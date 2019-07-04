package com.se231.onlineedu.controller;

import javax.validation.Valid;
import com.se231.onlineedu.message.request.CreateCoursePrototypeApplicationForm;
import com.se231.onlineedu.model.Apply;
import com.se231.onlineedu.model.CoursePrototype;
import com.se231.onlineedu.security.services.UserPrinciple;
import com.se231.onlineedu.service.CoursePrototypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Course Controller Class
 *
 * Controller Class of Courses.
 *
 * @author Zhe Li
 *
 * @date 2019/7/3
 */
@RestController
@RequestMapping("/api/course")
public class CoursePrototypeController {
    @Autowired
    private CoursePrototypeService coursePrototypeService;

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('TEACHING_ADMIN','ADMIN','SUPER_ADMIN')")
    public ResponseEntity<CoursePrototype> createCourse(@Valid @RequestBody CreateCoursePrototypeApplicationForm form, BindingResult bindingResult,
                                                        @AuthenticationPrincipal UserPrinciple userPrinciple) throws Exception{
        return ResponseEntity.ok(coursePrototypeService.createCourse(form,userPrinciple.getId()));
    }

    @PostMapping("/{id}/apply")
    @PreAuthorize("hasAnyRole('TEACHING_ADMIN','ADMIN','SUPER_ADMIN')")
    public ResponseEntity<Apply> applyForCourse(@PathVariable(name = "id")Long courseId,
                                                @AuthenticationPrincipal UserPrinciple userPrinciple) throws Exception {

        return ResponseEntity.ok(coursePrototypeService.applyForCourse(courseId,userPrinciple.getId()));
    }

    @PostMapping("/{id}/create")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public ResponseEntity<CoursePrototype> decideApplicationOfCreatingCourse(@RequestParam(name = "decision")String decision,
                                                  @PathVariable(name = "id") Long coursePrototypeId)throws Exception{
        return ResponseEntity.ok(coursePrototypeService.decideCreateCourse(coursePrototypeId,decision));
    }

    @PostMapping("/apply")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public ResponseEntity<Apply> decideApplicationOfUsingCourse(@RequestParam(name = "decision")String decision,
                                               @RequestParam(name = "course_id")Long courseId,
                                               @RequestParam(name = "applicant_id")Long applicantId)throws Exception{

        return ResponseEntity.ok(coursePrototypeService.decideUseCourse(courseId,applicantId,decision));
    }

}
