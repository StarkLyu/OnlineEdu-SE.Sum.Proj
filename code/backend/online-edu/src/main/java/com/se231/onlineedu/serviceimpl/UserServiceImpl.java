package com.se231.onlineedu.serviceimpl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import com.alibaba.excel.EasyExcelFactory;
import com.se231.onlineedu.message.response.PersonalInfo;
import com.se231.onlineedu.model.Role;
import com.se231.onlineedu.model.RoleType;
import com.se231.onlineedu.model.User;
import com.se231.onlineedu.model.UserExcel;
import com.se231.onlineedu.repository.RoleRepository;
import com.se231.onlineedu.repository.UserRepository;
import com.se231.onlineedu.service.EmailSenderService;
import com.se231.onlineedu.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



/**
 * User Service Implementation Class
 *
 * contain main service logic related to user.
 *
 * @author Zhe Li
 *
 * @date 2019/07/08
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder encoder;

    private RoleRepository roleRepository;

    @Autowired
    private EmailSenderService emailSenderService;
    public UserServiceImpl(UserRepository userRepository,PasswordEncoder encoder,RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.encoder=encoder;
        this.roleRepository=roleRepository;
    }

    @Override
    public User getUserInfo(Long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(()->new Exception("No corresponding user"));
        return user;
    }

    @Override
    public User manageUserInfo(Long id, PersonalInfo personalInfo) throws Exception {
        User user = userRepository.findById(id).orElseThrow(()->new Exception("No corresponding user"));
        checkSameEmailAndTel(personalInfo.getEmail(),personalInfo.getTel(),user);
        personalInfo.modifyUserInfo(user);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public boolean checkSameUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean checkSameEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean checkSameTel(String tel) {
        return userRepository.existsByTel(Long.parseLong(tel));
    }

    private void checkSameEmailAndTel(String email,String tel,User originUser)throws Exception{
        Long tele = Long.parseLong(tel);
        if(!originUser.getEmail().equals(email)&&userRepository.existsByEmail(email)){
            throw new Exception("This email address is already token !");
        }

        if(!originUser.getTel().equals(tele)&&userRepository.existsByTel(tele)){
            throw new Exception("This telephone number is already token !");
        }
    }

    @Override
    public String bulkImportUser(MultipartFile excel) throws Exception {
        Workbook workbook = null;
        //获取文件名字
        String fileName = excel.getOriginalFilename();
        //判断后缀
        if(fileName.endsWith("xls")){
            workbook = new HSSFWorkbook(excel.getInputStream());
        }else if(fileName.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(excel.getInputStream());
        }else {
            return "Import File Fail -> File Format Wrong,Only Support Xlsx And Xls";
        }
        //获取工作sheet
        Sheet sheet = workbook.getSheet("sheet1");
        int rows = sheet.getLastRowNum();
        if(rows==0||rows==1){
            return "File Error -> File Is Empty.";
        }

        List<Role> roles=new ArrayList<>();
        Role userRole = roleRepository.findByRole(RoleType.ROLE_USER).
                orElseThrow(()->new RuntimeException("Fail -> Cause: User Role Not Found"));
        roles.add(userRole);

        List<User> userList= new ArrayList<>();
        InputStream file = excel.getInputStream();
        List<Object> data = EasyExcelFactory
                .read(file, new com.alibaba.excel.metadata.Sheet(1,1, UserExcel.class));
        int rowNumber=1;
        //username,password,email,tel,university,major,sno,grade,real name,sex
        for(Object dataItem:data){
            rowNumber++;
            UserExcel userExcel=(UserExcel)dataItem;
            if(userRepository.existsByUsername(userExcel.getUsername())){
                return "Data Error -> Same Username In Row "+rowNumber;
            }
            if(userRepository.existsByEmail(userExcel.getEmail())){
                return "Data Error -> Same Email In Row "+rowNumber;
            }
            if(userRepository.existsByTel(userExcel.getTel())){
                return "Data Error -> Same Telephone Number In Row "+rowNumber;
            }
            User user =new User(userExcel);
            user.setPassword(encoder.encode((userExcel.getPassword())));
            userList.add(user);
        }

        userRepository.saveAll(userList);
        return "Import successfully.";
    }

    @Override
    public User updateUserAvatar(String avatarUrl, Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(()->new Exception("No corresponding user"));
        user.setAvatarUrl(avatarUrl);
        userRepository.save(user);
        return user;
    }

    @Override
    public String sendEmail(User user) throws Exception {
        Integer tokenInt = ThreadLocalRandom.current().nextInt(100000, 1000000);
        String token = tokenInt.toString();

        String subject = "验证码服务";
        String message = "您的验证码为：";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject(subject);
        email.setFrom("18621107375@163.com");
        email.setText(message + token);
        emailSenderService.sendEmail(email);
        return token;
    }

    @Override
    public User updateUserPasswordConfirm(Long id, String password) throws Exception {
        User user = userRepository.findById(id).orElseThrow(()->new Exception("No corresponding user"));
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    @Override
    public User updateUserEmailConfirm(Long id, String email) throws Exception {
        User user = userRepository.findById(id).orElseThrow(()->new Exception("No corresponding user"));
        user.setEmail(email);
        return userRepository.save(user);
    }
}
