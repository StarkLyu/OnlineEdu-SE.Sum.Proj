package com.se231.onlineedu.controller;

import com.alibaba.fastjson.JSONObject;
import com.se231.onlineedu.message.response.PersonalInfo;
import com.se231.onlineedu.model.User;
import com.se231.onlineedu.security.services.UserPrinciple;
import com.se231.onlineedu.service.EmailSenderService;
import com.se231.onlineedu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.util.List;

/**
 * User Controller Class
 *
 * controller used to handle request related to user.
 *
 * @author Zhe Li
 *
 * @date 2019/07/08
 */
@RestController
@RequestMapping("/api/users")
@Api(tags = "用户信息控制类",value = "用户信息相关的api")
@PropertySource(value={"classpath:user.properties"})
public class UserController {
    @Value("${app.nginx.path}")
    private String nginxPath;

    @Value("${app.file.limit}")
    private Long limit;

    private static String fileExtension = ".jpg,.jpeg,.png,.svg,.tif";

    @Autowired
    UserService userService;


    @ApiOperation(value = "已登录用户查询个人信息",notes = "已登录用户查询个人信息",httpMethod = "GET")
    @GetMapping("/info")
    public ResponseEntity<?> getPersonalInfo(@AuthenticationPrincipal UserPrinciple userPrinciple)throws Exception{
        return ResponseEntity.ok(userService.getUserInfo(userPrinciple.getId()));
    }

    @ApiOperation(value = "用户修改自己的个人信息",httpMethod = "POST")
    @PostMapping("/info/modify")
    public ResponseEntity<?> modifyPersonalInfo(@AuthenticationPrincipal UserPrinciple userPrinciple,
                                                           @Valid @RequestBody PersonalInfo personalInfo)throws Exception{
        return ResponseEntity.ok(userService.manageUserInfo(userPrinciple.getId(),personalInfo));
    }

    @ApiOperation(value = "管理员修改用户的个人信息",httpMethod = "POST")
    @PostMapping("{id}/info/modify")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public ResponseEntity<?> managePersonalInfo(@Valid @RequestBody PersonalInfo personalInfo,
                                                           @PathVariable("id")Long id)throws Exception{
        return ResponseEntity.ok(userService.manageUserInfo(id, personalInfo));
    }

    @ApiOperation(value = "管理员获取全部用户的信息",httpMethod = "GET")
    @GetMapping("/info/all")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @ApiOperation(value = "查询是否有重复的用户名",httpMethod = "GET")
    @ApiResponse(code = 200,response = Boolean.class,message = "真则为存在重复用户名")
    @ApiImplicitParam(name = "username",value = "待查的用户名",
            required = true,dataTypeClass = String.class)
    @GetMapping("/checkSame/username")
    public ResponseEntity<Boolean> checkSameUsername(@RequestParam("username")String username){
        return ResponseEntity.ok(userService.checkSameUsername(username));
    }

    @ApiOperation(value = "查询是否存在重复的邮箱地址",httpMethod = "GET")
    @ApiResponse(code = 200,response = Boolean.class,message = "真则为存在重复邮箱地址")
    @ApiImplicitParam(value = "待查询的邮箱地址",name = "email",
            required = true,dataTypeClass = String.class)
    @GetMapping("/checkSame/email")
    public ResponseEntity<Boolean> checkSameEmail(@RequestParam("email")String email){
        return ResponseEntity.ok(userService.checkSameEmail(email));
    }

    @ApiOperation(value = "查询是否存在重复的电话号码",httpMethod = "GET")
    @ApiResponse(code = 200,response = Boolean.class,message = "真则为存在重复电话号码")
    @ApiImplicitParam(value = "待查询的电话号码",name = "tel",
            required = true,dataTypeClass = String.class)
    @GetMapping("/checkSame/tel")
    public ResponseEntity<Boolean> checkSameTel(@RequestParam("tel")String tel){
        return ResponseEntity.ok(userService.checkSameTel(tel));
    }


    @ApiOperation(value = "用户修改个人的头像",httpMethod = "PATCH")
    @PatchMapping("/{id}/avatar")
    @PreAuthorize("#id == authentication.principal.id")
    public ResponseEntity<?> patchAvatar(@PathVariable Long id, @RequestParam(value = "avatar") MultipartFile multipartFile) throws Exception {
        if (multipartFile.getSize() > limit) {
            return ResponseEntity.badRequest().body("exceeded max size");
        }

        String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));

        if (!fileExtension.contains(suffix)) {
            return ResponseEntity.badRequest().body("file format not supported");
        }
        String fileName = nginxPath + id + "-avatar/" + id + "-avatar" + suffix;
        File file = new File(fileName);

        if (file.getParentFile().exists()) {
            FileUtils.cleanDirectory(file.getParentFile());
        } else {
            file.getParentFile().mkdir();
        }
        file.createNewFile();
        multipartFile.transferTo(file);

        return ResponseEntity.ok(userService.updateUserAvatar(id + "-avatar/" + id + "-avatar" + suffix, id));
    }


    @ApiOperation(value = "用户修改个人的密码",httpMethod = "PATCH")
    @PatchMapping("/{id}/password")
    @PreAuthorize("#id == authentication.principal.id")
    public ResponseEntity<?> patchPassword(@PathVariable Long id, HttpSession httpSession, @RequestBody JSONObject passwordJSON) throws Exception {
        httpSession.setAttribute("password", passwordJSON.get("password"));
        httpSession.setAttribute("token", userService.sendEmail(userService.getUserInfo(id)));
        return ResponseEntity.ok("已发送验证码");
    }

    @ApiOperation(value = "用户邮箱确认修改",httpMethod = "GET")
    @GetMapping("/{id}/password/confirm")
    @PreAuthorize("#id == authentication.principal.id")
    public ResponseEntity<?> patchPasswordConfirm(@PathVariable Long id, HttpSession httpSession, @RequestParam("verificationToken") String token) throws Exception {
        String testToken = (String)httpSession.getAttribute("token");
        if(testToken.equals(token)){
            String password = (String)httpSession.getAttribute("password");
            userService.updateUserPasswordConfirm(id,password);
            return ResponseEntity.ok("修改成功");
        }
        return ResponseEntity.badRequest().body("验证码错误");
    }


    @ApiOperation(value = "用户修改个人的邮箱",httpMethod = "PATCH")
    @PatchMapping("/{id}/email")
    @PreAuthorize("#id == authentication.principal.id")
    public ResponseEntity<?> patchEmail(@PathVariable Long id, HttpSession httpSession, @RequestBody JSONObject passwordJSON) throws Exception {
        httpSession.setAttribute("email", passwordJSON.get("email"));
        httpSession.setAttribute("token", userService.sendEmail(userService.getUserInfo(id)));
        return ResponseEntity.ok("已发送验证码");
    }

    @ApiOperation(value = "用户邮箱确认修改",httpMethod = "GET")
    @GetMapping("/{id}/email/confirm")
    @PreAuthorize("#id == authentication.principal.id")
    public ResponseEntity<?> patchEmailConfirm(@PathVariable Long id, HttpSession httpSession, @RequestParam("verificationToken") String token) throws Exception {
        String testToken = (String)httpSession.getAttribute("token");
        if(testToken.equals(token)){
            String email = (String)httpSession.getAttribute("email");
            userService.updateUserEmailConfirm(id,email);
            return ResponseEntity.ok("修改成功");
        }
        return ResponseEntity.badRequest().body("验证码错误");
    }
}
