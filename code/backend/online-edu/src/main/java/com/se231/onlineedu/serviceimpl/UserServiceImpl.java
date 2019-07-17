package com.se231.onlineedu.serviceimpl;

import com.alibaba.excel.EasyExcelFactory;
import com.se231.onlineedu.message.request.SignInCourseForm;
import com.se231.onlineedu.message.request.SignInUserForm;
import com.se231.onlineedu.message.response.PersonalInfo;
import com.se231.onlineedu.model.*;
import com.se231.onlineedu.repository.CourseRepository;
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
    private PasswordEncoder encoder;

    @Autowired
    private SignInRepository signInRepository;

    @Autowired
    private CourseService courseService;

    @Override
    public User getUserInfo(Long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(()->new Exception("No corresponding user"));
        return user;
    }

    @Override
    public User manageUserInfo(Long id, PersonalInfo personalInfo) throws Exception {
        User user = userRepository.findById(id).orElseThrow(()->new Exception("No corresponding user"));
        if(!user.getTel().toString().equals(personalInfo.getTel())&&checkSameTel(personalInfo.getTel())){
            throw new Exception("This telephone number is already token !");
        }
        checkSameTel(personalInfo.getTel());
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
    public ResponseEntity<String> bulkImportUser(MultipartFile excel) throws Exception {
        Workbook workbook = null;
        //获取文件名字
        String fileName = excel.getOriginalFilename();
        //判断后缀
        if(fileName.endsWith("xls")){
            workbook = new HSSFWorkbook(excel.getInputStream());
        }else if(fileName.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(excel.getInputStream());
        }else {
            return ResponseEntity.badRequest().body("Import File Fail -> File Format Wrong,Only Support Xlsx And Xls");
        }
        //获取工作sheet
        Sheet sheet = workbook.getSheet("sheet1");
        int rows = sheet.getLastRowNum();
        if(rows==0){
            return ResponseEntity.badRequest().body("File Error -> File Is Empty.");
        }

        List<Role> roles=new ArrayList<>();
        Role userRole = roleRepository.findByRole(RoleType.ROLE_USER).
                orElseThrow(()->new RuntimeException("Fail -> Cause: User Role Not Found"));
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
            user.setPassword(encoder.encode((userExcel.getPassword())));
            userRepository.save(user);
        }

        if(!hasError) {
            return ResponseEntity.ok("Import successfully.");
        }
        else {
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }
    }

    @Override
    public User updateUserAvatar(String avatarUrl, Long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(()->new Exception("No corresponding user"));
        user.setAvatarUrl(avatarUrl);
        userRepository.save(user);
        return user;
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

    @Override
    public ResponseEntity<?> saveUserSignIn(Long id, SignInUserForm signInUserForm) throws Exception {
        User user = getUserInfo(id);
        SignInPrimaryKey signInPrimaryKey = new SignInPrimaryKey(courseService.getCourseInfo(signInUserForm.getCourseId()),signInUserForm.getSignInNo());
        SignIn signIn = signInRepository.findById(signInPrimaryKey).orElseThrow(()-> new Exception("Sign in not found"));
        Date date = new Date();
        if(date.before(signIn.getStartDate())){
            return ResponseEntity.badRequest().body("签到还未开始");
        }
        if(date.after(signIn.getEndDate())){
            return ResponseEntity.badRequest().body("签到已经结束");
        }
        user.getSignIns().add(signIn);
        return ResponseEntity.ok(userRepository.save(user));
    }
}
