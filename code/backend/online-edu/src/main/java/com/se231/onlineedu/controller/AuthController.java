package com.se231.onlineedu.controller;

import javax.validation.Valid;
import com.se231.onlineedu.message.request.LoginForm;
import com.se231.onlineedu.message.request.SignUpForm;
import com.se231.onlineedu.model.User;
import com.se231.onlineedu.model.VerificationToken;
import com.se231.onlineedu.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Calendar;
import java.util.Locale;

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
    public ResponseEntity<?> registerUser(WebRequest webRequest, @Valid @RequestBody SignUpForm signUpRequest) {
        return authService.userSignUp(signUpRequest, webRequest);
    }

    @PostMapping("/{id}/teachingAdmin")
    @PreAuthorize("hasAnyRole('ADMIN','SPUER_ADMIN')")
    public ResponseEntity<String> addTeachingAdmin(@PathVariable(name = "id")Long id){
        return authService.addTeachingAdmin(id);
    }

    @GetMapping("/registrationConfirm")
    public ResponseEntity<String> confirmRegistration(WebRequest webRequest, @RequestParam("token") String token){
        Locale locale = webRequest.getLocale();
        VerificationToken verificationToken = authService.getVerificationToken(token);
        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return ResponseEntity.badRequest().body("验证码已失效");
        }

        user.setEnabled(true);
        authService.saveRegisteredUser(user);
        return ResponseEntity.ok("已激活");
    }
}