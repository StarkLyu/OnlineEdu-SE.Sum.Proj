package com.se231.onlineedu.controller;

import javax.validation.Valid;
import java.util.List;
import com.se231.onlineedu.message.response.PersonalInfo;
import com.se231.onlineedu.model.User;
import com.se231.onlineedu.security.services.UserPrinciple;
import com.se231.onlineedu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
@Api(tags = "用户信息控制类",value = "用户信息相关的api")
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "已登录用户查询个人信息",notes = "已登录用户查询个人信息",httpMethod = "GET")
    @GetMapping("/info")
    public ResponseEntity<PersonalInfo> getPersonalInfo(@AuthenticationPrincipal UserPrinciple userPrinciple)throws Exception{
        return ResponseEntity.ok(userService.getUserInfo(userPrinciple.getId()));
    }

    @ApiOperation(value = "用户修改自己的个人信息",httpMethod = "POST")
    @PostMapping("/info/modify")
    public ResponseEntity<PersonalInfo> modifyPersonalInfo(@AuthenticationPrincipal UserPrinciple userPrinciple,
                                                           @Valid @RequestBody PersonalInfo personalInfo)throws Exception{
        return ResponseEntity.ok(userService.manageUserInfo(userPrinciple.getId(),personalInfo));
    }

    @ApiOperation(value = "管理员修改用户的个人信息",httpMethod = "POST")
    @PostMapping("{id}/info/modify")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public ResponseEntity<PersonalInfo> managePersonalInfo(@Valid @RequestBody PersonalInfo personalInfo,
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

}
