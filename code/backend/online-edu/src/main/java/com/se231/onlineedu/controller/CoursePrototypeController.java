package com.se231.onlineedu.controller;

import javax.validation.Valid;
import com.se231.onlineedu.message.request.CreateCoursePrototypeApplicationForm;
import com.se231.onlineedu.model.Apply;
import com.se231.onlineedu.model.CoursePrototype;
import com.se231.onlineedu.security.services.UserPrinciple;
import com.se231.onlineedu.service.CoursePrototypeService;
import io.swagger.annotations.*;
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
@Api(tags = "与课程原型相关的控制类")
@RestController
@RequestMapping("/api/coursePrototypes")
public class CoursePrototypeController {
    @Autowired
    private CoursePrototypeService coursePrototypeService;

    @ApiOperation(value = "教学管理员创建课程原型申请")
    @PostMapping("/")
    @PreAuthorize("hasAnyRole('TEACHING_ADMIN','ADMIN','SUPER_ADMIN')")
    public ResponseEntity<CoursePrototype> createCoursePrototype(@Valid @RequestBody CreateCoursePrototypeApplicationForm form,
                                                         @AuthenticationPrincipal UserPrinciple userPrinciple) throws Exception{
        return ResponseEntity.ok(coursePrototypeService.createCourse(form,userPrinciple.getId()));
    }

    @ApiOperation("教师申请使用他人创建的课程原型")
    @ApiImplicitParam(name = "id",value = "申请的课程原型的id",paramType = "path")
    @PostMapping("/{id}/apply")
    @PreAuthorize("hasAnyRole('TEACHING_ADMIN','ADMIN','SUPER_ADMIN')")
    public ResponseEntity<Apply> applyForCoursePrototype(@PathVariable(name = "id")Long coursePrototypeId,
                                                @AuthenticationPrincipal UserPrinciple userPrinciple) throws Exception {

        return ResponseEntity.ok(coursePrototypeService.applyForCourse(coursePrototypeId,userPrinciple.getId()));
    }

    @ApiOperation("管理员审核教师对创建课程原型的申请")
    @ApiImplicitParams ({
        @ApiImplicitParam(name = "id",value = "待审核的课程原型的id",paramType = "path"),
        @ApiImplicitParam(name = "decision",value = "决定",paramType = "param",examples = @Example({
                @ExampleProperty(value = "approval"),
                @ExampleProperty(value = "disapproval")
        }))
    })
    @PostMapping("/{id}/create")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public ResponseEntity<CoursePrototype> decideApplicationOfCreatingCoursePrototype(@RequestParam(name = "decision")String decision,
                                                  @PathVariable(name = "id") Long coursePrototypeId)throws Exception{
        return ResponseEntity.ok(coursePrototypeService.decideCreateCourse(coursePrototypeId,decision));
    }

    @ApiOperation("管理员审核教师对某一课程原型的使用权的申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "coursePrototype_id",value = "待审核的申请的课程原型id",paramType = "param"),
            @ApiImplicitParam(name = "applicant_id",value = "申请者的id",paramType = "param"),
            @ApiImplicitParam(name = "decision",value = "决定",paramType = "param",example = "approval/disapproval")
    })
    @PostMapping("/apply")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public ResponseEntity<Apply> decideApplicationOfUsingCoursePrototype(@RequestParam(name = "decision")String decision,
                                               @RequestParam(name = "coursePrototype_id")Long courseId,
                                               @RequestParam(name = "applicant_id")Long applicantId)throws Exception{

        return ResponseEntity.ok(coursePrototypeService.decideUseCourse(courseId,applicantId,decision));
    }

}
