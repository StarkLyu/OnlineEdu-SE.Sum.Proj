package com.se231.onlineedu.controller;

import javax.validation.Valid;
import java.util.List;
import com.se231.onlineedu.message.response.PersonalInfo;
import com.se231.onlineedu.model.User;
import com.se231.onlineedu.security.services.UserPrinciple;
import com.se231.onlineedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/info")
    public ResponseEntity<PersonalInfo> getPersonalInfo(@AuthenticationPrincipal UserPrinciple userPrinciple)throws Exception{
        return ResponseEntity.ok(userService.getUserInfo(userPrinciple.getId()));
    }

    @PostMapping("/info/modify")
    public ResponseEntity<PersonalInfo> modifyPersonalInfo(@AuthenticationPrincipal UserPrinciple userPrinciple,
                                                           @Valid @RequestBody PersonalInfo personalInfo)throws Exception{
        return ResponseEntity.ok(userService.modifyUserInfo(userPrinciple.getId(),personalInfo));
    }

    @PostMapping("{id}/info/modify")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public ResponseEntity<PersonalInfo> managePersonalInfo(@Valid @RequestBody PersonalInfo personalInfo,
                                                           @PathVariable("id")Long id)throws Exception{
        return ResponseEntity.ok(userService.manageUserInfo(id, personalInfo));
    }

    @GetMapping("/")
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
}
