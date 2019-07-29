package com.se231.onlineedu.controller;

import com.se231.onlineedu.exception.EndBeforeStartException;
import com.se231.onlineedu.message.request.SignInCourseForm;
import com.se231.onlineedu.message.request.SignInUserForm;
import com.se231.onlineedu.model.Course;
import com.se231.onlineedu.model.User;
import com.se231.onlineedu.service.CourseService;
import com.se231.onlineedu.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liu
 * @date 2019/07/11
 */
@RestController
@RequestMapping("/api")
public class SignInController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;


    @ApiOperation("教师发布签到")
    @PostMapping("/courses/{courseId}/signIns/")
    public Course postSignIn(@PathVariable Long courseId, @RequestBody SignInCourseForm signInForm) {
        if(signInForm.getStartDate().after(signInForm.getEndDate())){
           throw new EndBeforeStartException("开始时间晚于结束时间");
        }
        return courseService.saveSignIn(courseId, signInForm);
    }

    @ApiOperation("学生签到")
    @PostMapping("/users/{userId}/signIns")
    public User userSignIn(@PathVariable Long userId, @RequestBody SignInUserForm signInUserForm) {
        return userService.saveUserSignIn(userId, signInUserForm);
    }
}
