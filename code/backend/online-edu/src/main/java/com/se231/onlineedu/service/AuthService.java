package com.se231.onlineedu.service;

import com.se231.onlineedu.message.request.SignUpForm;
import com.se231.onlineedu.model.User;
import com.se231.onlineedu.model.VerificationToken;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;

/**
 * Interface of service related to auth.
 * <p>
 * contains interface of main auth service.
 *
 * @author Zhe Li
 * @date 2019/07/08
 */
public interface AuthService {

    /**
     * this service allow user to login
     *
     * @param username input username
     * @param password input password
     * @return jwt response for specific user
     */
    ResponseEntity<?> userSignIn(String username, String password);

    /**
     * this service allow user to register
     *
     * @param form register form
     * @return a http response entity
     */
    ResponseEntity<?> userSignUp(SignUpForm form,  HttpSession httpSession) throws Exception;

    ResponseEntity<String> addTeachingAdmin(Long userId);

    void saveRegisteredUser(User user);

}
