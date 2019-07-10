package com.se231.onlineedu.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.se231.onlineedu.message.response.PersonalInfo;
import com.se231.onlineedu.model.User;
import com.se231.onlineedu.security.services.UserPrinciple;
import com.se231.onlineedu.service.EmailSenderService;
import com.se231.onlineedu.service.UserService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
@PropertySource(value={"classpath:user.properties"})
public class UserController {
    @Value("${app.nginx.path}")
    private String nginxPath;

    @Value("${app.file.limit}")
    private Long limit;

    static String fileExtension = ".jpg,.jpeg,.png,.svg,.tif";

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    UserService userService;

    @GetMapping("/self-info")
    public ResponseEntity<?> getPersonalInfo(@AuthenticationPrincipal UserPrinciple userPrinciple)throws Exception{
        return ResponseEntity.ok(userService.getUserInfo(userPrinciple.getId()));
    }

    @PostMapping("/info/modify")
    public ResponseEntity<?> modifyPersonalInfo(@AuthenticationPrincipal UserPrinciple userPrinciple,
                                                           @Valid @RequestBody PersonalInfo personalInfo)throws Exception{
        return ResponseEntity.ok(userService.manageUserInfo(userPrinciple.getId(),personalInfo));
    }

    @PostMapping("{id}/info/modify")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public ResponseEntity<?> managePersonalInfo(@Valid @RequestBody PersonalInfo personalInfo,
                                                           @PathVariable("id")Long id)throws Exception{
        return ResponseEntity.ok(userService.manageUserInfo(id, personalInfo));
    }

    @GetMapping("/info")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @GetMapping("/checkSame/username")
    public ResponseEntity<Boolean> checkSameUsername(@RequestParam("username")String username){
        return ResponseEntity.ok(userService.checkSameUsername(username));
    }

    @GetMapping("/checkSame/email")
    public ResponseEntity<Boolean> checkSameEmail(@RequestParam("email")String email){
        return ResponseEntity.ok(userService.checkSameUsername(email));
    }

    @GetMapping("/checkSame/tel")
    public ResponseEntity<Boolean> checkSameTel(@RequestParam("tel")String tel){
        return ResponseEntity.ok(userService.checkSameUsername(tel));
    }

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

    @PatchMapping("/{id}/password")
    @PreAuthorize("#id == authentication.principal.id")
    public ResponseEntity<?> patchPassword(@PathVariable Long id, HttpSession httpSession, @RequestBody JSONObject passwordJSON) throws Exception {
        httpSession.setAttribute("password", passwordJSON.get("password"));
        httpSession.setAttribute("token", userService.sendEmail(userService.getUserInfo(id)));
        return ResponseEntity.ok("已发送验证码");
    }

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


    @PatchMapping("/{id}/email")
    @PreAuthorize("#id == authentication.principal.id")
    public ResponseEntity<?> patchEmail(@PathVariable Long id, HttpSession httpSession, @RequestBody JSONObject passwordJSON) throws Exception {
        httpSession.setAttribute("email", passwordJSON.get("email"));
        httpSession.setAttribute("token", userService.sendEmail(userService.getUserInfo(id)));
        return ResponseEntity.ok("已发送验证码");
    }

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
