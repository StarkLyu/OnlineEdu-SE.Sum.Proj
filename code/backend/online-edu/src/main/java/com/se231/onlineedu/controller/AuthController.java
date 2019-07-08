package com.se231.onlineedu.controller;

import javax.validation.Valid;
import com.se231.onlineedu.message.request.LoginForm;
import com.se231.onlineedu.message.request.SignUpForm;
import com.se231.onlineedu.service.AuthService;
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
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
        return ResponseEntity.ok(authService.userSignIn(loginRequest.getUsername(),loginRequest.getPassword()));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        return authService.userSignUp(signUpRequest);
    }

    @PostMapping("/{id}/teachingAdmin")
    @PreAuthorize("hasAnyRole('ADMIN','SPUER_ADMIN')")
    public ResponseEntity<String> addTeachingAdmin(@PathVariable(name = "id")Long id){
        return authService.addTeachingAdmin(id);
    }
}