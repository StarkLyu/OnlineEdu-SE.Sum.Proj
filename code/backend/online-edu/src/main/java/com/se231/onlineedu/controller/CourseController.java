package com.se231.onlineedu.controller;

import javax.validation.Valid;
import com.se231.onlineedu.message.request.CreateCourseApplicationForm;
import com.se231.onlineedu.message.response.ApplyResponse;
import com.se231.onlineedu.model.User;
import com.se231.onlineedu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('TEACHING_ADMIN','ADMIN','SUPER_ADMIN')")
    public ResponseEntity<ApplyResponse> createCourse(@Valid @RequestBody CreateCourseApplicationForm form, BindingResult bindingResult,
                                                      @AuthenticationPrincipal User user) throws Exception{
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(new ApplyResponse(0), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(courseService.createCourse(form,user),HttpStatus.OK);
    }

    @PostMapping("/{id}/apply")
    @PreAuthorize("hasAnyRole('TEACHING_ADMIN','ADMIN','SUPER_ADMIN')")
    public ResponseEntity<ApplyResponse> applyForCourse(@PathVariable(name = "id")Long courseId,
                                                        @AuthenticationPrincipal User user) throws Exception {
        ApplyResponse response= courseService.applyForCourse(courseId,user);
        switch(response.getAlert()){
            case 0:
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            case 1:
                return new ResponseEntity<>(response,HttpStatus.OK);

            default:
                return new ResponseEntity<>(response,HttpStatus.CONTINUE);
        }
    }

    @PostMapping("/{id}/create")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public void decideApplicationOfCreatingCourse(@RequestParam(name = "decision")int decision,
                                                  @PathVariable(name = "id") Long coursePrototypeId)throws Exception{
        courseService.decideCreateCourse(coursePrototypeId,decision);
    }

    @PostMapping("/apply")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public void decideApplicationOfUsingCourse(@RequestParam(name = "decision")int decision,
                                               @RequestParam(name = "course_id")Long courseId,
                                               @RequestParam(name = "applicant_id")Long applicantId)throws Exception{

        courseService.decideUseCourse(courseId,applicantId,decision);
    }

}
