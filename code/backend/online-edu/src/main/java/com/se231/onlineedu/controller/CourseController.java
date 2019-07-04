package com.se231.onlineedu.controller;

import javax.validation.Valid;
import java.util.List;
import com.se231.onlineedu.message.request.CreateCourseApplicationForm;
import com.se231.onlineedu.model.Course;
import com.se231.onlineedu.model.CourseState;
import com.se231.onlineedu.model.PickCourse;
import com.se231.onlineedu.security.services.UserPrinciple;
import com.se231.onlineedu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * Course Controller Class
 * controller of the api related to course(concrete course)
 *
 * @author Zhe Li
 * @date 2019/7/4
 */
@RestController
@RequestMapping("api/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping("{id}/start")
    @PreAuthorize("hasAnyRole('TEACHING_ADMIN','ADMIN','SUPER_ADMIN')")
    public ResponseEntity<Course> applyToStartCourse(@PathVariable(name = "id")Long prototypeId,
                                                     @Valid @RequestBody CreateCourseApplicationForm form,
                                                     @AuthenticationPrincipal UserPrinciple userPrinciple) throws Exception{
        return ResponseEntity.ok(courseService.applyToStartCourse(prototypeId,form.getStartDate(),form.getEndDate(),userPrinciple.getId()));
    }

    @PostMapping("/start")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Course examineStartCourse(@RequestParam String decision,
                                     @RequestParam Long courseId) throws Exception{
        return courseService.examineStartCourseApplication(courseId, decision);
    }


    @PostMapping("/{id}/pick")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Course>> pickCourse(@PathVariable(name = "id")Long id,
                                   @AuthenticationPrincipal UserPrinciple userPrinciple)throws Exception{
        return ResponseEntity.ok(courseService.pickCourse(userPrinciple.getId(),id));
    }

}
