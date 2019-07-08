package com.se231.onlineedu.service;

import com.se231.onlineedu.message.request.SignUpForm;
import com.se231.onlineedu.message.response.JwtResponse;
import org.springframework.http.ResponseEntity;

/**
 * Interface of service related to auth.
 *
 * contains interface of main auth service.
 *
 * @author Zhe Li
 *
 * @date 2019/07/08
 */
public interface AuthService {

    /**
     * this service allow user to login
     * @param username  input username
     * @param password  input password
     * @return  jwt response for specific user
     */
    JwtResponse userSignIn(String username,String password);

    /**
     * this service allow user to register
     * @param form register form
     * @return  a http response entity
     */
    ResponseEntity<String> userSignUp(SignUpForm form);

    ResponseEntity<String> addTeachingAdmin(Long userId);
}
