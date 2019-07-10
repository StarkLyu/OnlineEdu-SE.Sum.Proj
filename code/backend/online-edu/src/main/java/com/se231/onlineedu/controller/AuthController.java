package com.se231.onlineedu.controller;

import javax.validation.Valid;
import com.se231.onlineedu.message.request.LoginForm;
import com.se231.onlineedu.message.request.SignUpForm;
import com.se231.onlineedu.service.AuthService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * AuthController Class
 *
 * log in and sign up controller
 *
 * need refraction
 *
 * @author Yuxuan Liu
 *
 * @date 2019/07/01
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Api(tags = "与用户权限有关的控制类")
public class AuthController {

    @Autowired
    AuthService authService;

    @ApiOperation(value = "用户登录", notes = "用户提供用户名和密码用于登录")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Error -> Unauthorized"),
            @ApiResponse(code = 200, message = "sign in successfully")
    })
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
        return ResponseEntity.ok(authService.userSignIn(loginRequest.getUsername(),loginRequest.getPassword()));
    }


    @ApiOperation(value = "用户注册",notes = "用户填写个人信息注册")
    @ApiResponses({
            @ApiResponse(code = 200, message = "sign up successfully"),
            @ApiResponse(code = 400, message = "sign up unsuccessfully," +
                    "mainly because of existing same username/email/tel,or invalid param"),
    })
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        return authService.userSignUp(signUpRequest);
    }

    @ApiOperation(value = "管理员任命教师",notes = "管理员任命用户为教师")
    @ApiImplicitParam(value = "被任命用户的id",dataType = "Long",
            paramType = "path",required = true)
    @PostMapping("/{id}/teachingAdmin")
    @PreAuthorize("hasAnyRole('ADMIN','SPUER_ADMIN')")
    public ResponseEntity<String> addTeachingAdmin(@PathVariable(name = "id")Long id){
        return authService.addTeachingAdmin(id);
    }
}