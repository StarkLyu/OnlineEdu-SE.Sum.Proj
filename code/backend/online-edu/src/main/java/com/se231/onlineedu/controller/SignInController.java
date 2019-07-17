package com.se231.onlineedu.controller;

import com.se231.onlineedu.model.Course;
import com.se231.onlineedu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SignInController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/courses/{courseId}/signIn")
    public Course postSignIn(@PathVariable Long courseId) throws Exception {
        Course course = courseService.getCourseInfo(courseId);
        return course;
    }
}
