package com.se231.onlineedu.controller;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import com.se231.onlineedu.message.request.CourseApplicationForm;
import com.se231.onlineedu.message.response.CourseWithIdentity;
import com.se231.onlineedu.model.Course;
import com.se231.onlineedu.model.User;
import com.se231.onlineedu.security.services.UserPrinciple;
import com.se231.onlineedu.service.CourseService;
import com.se231.onlineedu.util.FileCheckUtil;
import com.se231.onlineedu.util.SaveFileUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Course Controller Class
 * controller of the api related to course(concrete course)
 *
 * @author Zhe Li
 * @date 2019/7/4
 */
@Api(tags = "课程相关的控制类")
@RestController
@RequestMapping("api/courses")
public class CourseController {

    @Value("${app.file.limit}")
    private static int limit;

    @Autowired
    CourseService courseService;

    @ApiOperation(value = "教师基于已有的课程原型申请开课")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "prototypeId",value = "该课程基于的课程原型的id",paramType = "param"),
            @ApiImplicitParam(name = "form",paramType = "requestBody")
    })
    @PostMapping("/start")
    @PreAuthorize("hasAnyRole('TEACHING_ADMIN','ADMIN','SUPER_ADMIN')")
    public ResponseEntity<Course> applyToStartCourse(@RequestParam("prototypeId") Long prototypeId,
                                                     @Valid @RequestBody CourseApplicationForm form,
                                                     @AuthenticationPrincipal UserPrinciple userPrinciple) throws Exception{
        return ResponseEntity.ok(courseService.applyToStartCourse(form,prototypeId,userPrinciple.getId()));
    }

    @ApiOperation(value = "管理员审核教师的开课申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value ="生成的课程的id",paramType = "path"),
            @ApiImplicitParam(name = "decision",value = "决定",examples = @Example({
                    @ExampleProperty(value = "disapproval"),
                    @ExampleProperty(value = "approval")}))
    })
    @PostMapping("{id}/start")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER_ADMIN')")
    public Course examineStartCourse(@RequestParam String decision,
                                     @PathVariable("id") Long courseId) throws Exception{
        return courseService.examineStartCourseApplication(courseId, decision);
    }


    @ApiOperation("学生选课")
    @ApiImplicitParam(name = "id",value = "选课的学生的id",paramType = "param")
    @PostMapping("/{id}/pick")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Course>> pickCourse(@PathVariable(name = "id")Long id,
                                   @AuthenticationPrincipal UserPrinciple userPrinciple)throws Exception{
        return ResponseEntity.ok(courseService.pickCourse(userPrinciple.getId(),id));
    }

    @ApiOperation("获取某课程的学生名单")
    @ApiImplicitParam(name = "id",value = "获取的课程的id",type = "path")
    @GetMapping("/{id}/students")
    public ResponseEntity<List<User>> getStudentsList(@PathVariable(name = "id")Long courseId)throws Exception{
        return ResponseEntity.ok(courseService.getStudentsList(courseId));
    }

    @ApiImplicitParam(name = "id",value = "获取的课程的id",type = "path")
    @ApiOperation("获取某课程的信息")
    @GetMapping("/{id}/info")
    public ResponseEntity<CourseWithIdentity> getCourseInfo(@PathVariable(name = "id")Long courseId, @AuthenticationPrincipal UserPrinciple userPrinciple)throws Exception{
        return ResponseEntity.ok(courseService.getCourseInfoWithIdentity(courseId, userPrinciple.getId()));
    }

    @ApiOperation("获取所有课程的信息")
    @GetMapping("/all/info")
    public ResponseEntity<List<Course>> getAllCourse(){
        return ResponseEntity.ok(courseService.getAllCourse());
    }

    @ApiOperation(value = "用户可以课程的头像", httpMethod = "POST")
    @ApiImplicitParam(name = "id", value = "上传的课程id", type = "path")
    @PostMapping("/{id}/avatar")
    @PreAuthorize("#id == authentication.principal.id")
    public ResponseEntity<?> patchAvatar(@PathVariable Long id, @RequestParam(value = "avatar") MultipartFile multipartFile) throws Exception {
        if (FileCheckUtil.checkImageSizeExceed(multipartFile,limit)) {
            return ResponseEntity.badRequest().body("exceeded max size");
        }
        String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        if (FileCheckUtil.checkImageTypeWrong(suffix)) {
            return ResponseEntity.badRequest().body("file format not supported");
        }
        return ResponseEntity.ok(courseService.updateCourseAvatar(SaveFileUtil.saveAvatar(id, multipartFile,suffix, "course"), id));
    }

    @ApiOperation("查询用户是否选了某门课")
    @GetMapping("/{id}/isPicked")
    public ResponseEntity<Boolean> checkWhetherPicked(@AuthenticationPrincipal UserPrinciple userPrinciple,
                                                      @PathVariable("id")Long id)throws Exception{
        return ResponseEntity.ok(courseService.checkIfUserPick(id,userPrinciple.getId()));
    }

    @ApiOperation("管理员或教师修改课程信息")
    @PutMapping("/{id}/modify")
    public ResponseEntity<Course> modifyCourseInfo(@AuthenticationPrincipal UserPrinciple userPrinciple,
                                                   @Valid @RequestBody CourseApplicationForm form,
                                                   @PathVariable("id")Long id)throws Exception{
        return ResponseEntity.ok(courseService.modifyCourseInfo(id,form,userPrinciple.getId()));
    }
}
