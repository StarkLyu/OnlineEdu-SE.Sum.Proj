package com.se231.onlineedu.serviceimpl;

import com.alibaba.excel.EasyExcelFactory;
import com.se231.onlineedu.exception.*;
import com.se231.onlineedu.message.request.SignInUserForm;
import com.se231.onlineedu.message.response.PersonalInfo;
import com.se231.onlineedu.model.*;
import com.se231.onlineedu.repository.RoleRepository;
import com.se231.onlineedu.repository.SignInRepository;
import com.se231.onlineedu.repository.UserRepository;
import com.se231.onlineedu.service.CourseService;
import com.se231.onlineedu.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



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

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SignInRepository signInRepository;

    @Autowired
    private CourseService courseService;

    @Override
    public User getUserInfo(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("该用户不存在"));
        return user;
    }

    @Override
    public User manageUserInfo(Long id, PersonalInfo personalInfo){
        User user = getUserInfo(id);
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

    @Override
    public String bulkImportUser(MultipartFile excel) throws IOException {
        Workbook workbook = null;
        //获取文件名字
        String fileName = excel.getOriginalFilename();
        //判断后缀
        if(fileName.endsWith("xls")){
            workbook = new HSSFWorkbook(excel.getInputStream());
        }else if(fileName.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(excel.getInputStream());
        }else {
            throw new FileFormatNotSupportException("Import File Fail -> File Format Wrong,Only Support Xlsx And Xls");
        }
        //获取工作sheet
        Sheet sheet = workbook.getSheet("sheet1");
        int rows = sheet.getLastRowNum();
        if(rows==0){
            throw new EmptyFileException("File Error -> File Is Empty.");
        }

        List<Role> roles=new ArrayList<>();
        Role userRole = new Role(RoleType.ROLE_USER);
        roles.add(userRole);

        InputStream file = excel.getInputStream();
        List<Object> data = EasyExcelFactory
                .read(file, new com.alibaba.excel.metadata.Sheet(1,1, UserExcel.class));
        int rowNumber=1;
        boolean hasError = false;
        StringBuilder errorMessage= new StringBuilder();
        errorMessage.append("Some Data Has Error:\n");
        //username,password,email,tel,university,major,sno,grade,real name,sex
        for(Object dataItem:data){
            rowNumber++;
            UserExcel userExcel=(UserExcel)dataItem;
            if(userExcel.getPassword() == null){
                continue;
            }
            if(userRepository.existsByUsername(userExcel.getUsername())){
                hasError=true;
                errorMessage.append("Data Error -> Same Username In Row "+rowNumber+"\n");
                continue;
            }
            if(userRepository.existsByEmail(userExcel.getEmail())){
                hasError=true;
                errorMessage.append("Data Error -> Same Email In Row "+rowNumber+"\n");
                continue;
            }
            if(userRepository.existsByTel(userExcel.getTel())){
                hasError=true;
                errorMessage.append("Data Error -> Same Telephone Number In Row "+rowNumber+"\n");
                continue;
            }
            User user =new User(userExcel);
            user.setRoles(roles);
            user.setPassword(passwordEncoder.encode(userExcel.getPassword()));
            userRepository.save(user);
        }

        if(!hasError) {
            return "导入成功";
        }
        else {
            throw new BulkImportDataException(errorMessage.toString());
        }
    }

    @Override
    public User updateUserAvatar(String avatarUrl, Long id) {
        User user = getUserInfo(id);
        user.setAvatarUrl(avatarUrl);
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUserPasswordConfirm(Long id, String password) {
        User user = getUserInfo(id);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    @Override
    public User updateUserEmailConfirm(Long id, String email) {
        User user = getUserInfo(id);
        user.setEmail(email);
        return userRepository.save(user);
    }

    @Override
    public User saveUserSignIn(Long id, SignInUserForm signInUserForm){
        User user = getUserInfo(id);
        SignInPrimaryKey signInPrimaryKey = new SignInPrimaryKey(courseService.getCourseInfo(signInUserForm.getCourseId()),signInUserForm.getSignInNo());
        SignIn signIn = signInRepository.findById(signInPrimaryKey).orElseThrow(()-> new NotFoundException("该签到不存在"));
        Date date = new Date();
        if(date.before(signIn.getStartDate())){
            throw new BeforeStartException("签到还未开始");
        }
        if(date.after(signIn.getEndDate())){
            throw new AfterEndException("签到已经结束");
        }
        user.getSignIns().add(signIn);
        return userRepository.save(user);
    }

    @Override
    public boolean checkIfSameAsOldPassword(Long id, String password) {
        User user = getUserInfo(id);
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public boolean checkIfSameAsOldTel(Long id,Long tel) {
        User user = getUserInfo(id);
        return tel.equals(user.getTel());
    }

    @Override
    public boolean checkIfSameAsOldEmail(Long id, String Email){
        User user = getUserInfo(id);
        return Email.equals(user.getEmail());
    }
}
