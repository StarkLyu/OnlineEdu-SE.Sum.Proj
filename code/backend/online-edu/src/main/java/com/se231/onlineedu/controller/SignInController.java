package com.se231.onlineedu.controller;

import com.se231.onlineedu.message.request.SignInCourseForm;
import com.se231.onlineedu.message.request.SignInUserForm;
import com.se231.onlineedu.service.CourseService;
import com.se231.onlineedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SignInController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @PostMapping("/courses/{courseId}/signIns/")
    public ResponseEntity<?> postSignIn(@PathVariable Long courseId, @RequestBody SignInCourseForm signInForm) throws Exception {
        if(signInForm.getStartDate().after(signInForm.getEndDate())){
            return ResponseEntity.badRequest().body("开始时间必须早于结束时间");
        }
        return ResponseEntity.ok(courseService.saveSignIn(courseId, signInForm));
    }

    @PostMapping("/users/{userId}/signIns")
    public ResponseEntity<?> userSignIn(@PathVariable Long userId, @RequestBody SignInUserForm signInUserForm) throws Exception {
        return userService.saveUserSignIn(userId, signInUserForm);
    }
}
