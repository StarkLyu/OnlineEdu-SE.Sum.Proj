package com.se231.onlineedu.controller;

import javax.validation.Valid;
import com.se231.onlineedu.message.request.TitleAndDes;
import com.se231.onlineedu.model.Section;
import com.se231.onlineedu.model.SectionBranches;
import com.se231.onlineedu.service.SectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zhe Li
 * @date 2019/07/19
 */
@Api
@RestController
@RequestMapping("api/courses/{courseId}/sections")
public class SectionController {
    @Autowired
    SectionService sectionService;

    @ApiOperation("创建章节")
    @PostMapping("/append")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId",value = "课程id",paramType = "path"),
            @ApiImplicitParam(name = "secNo",value = "插入的章节的前一章节的序号",paramType = "param")
    })
    public Section createSection(@PathVariable("courseId")Long courseId,
                                 @RequestParam("secNo")Integer secNo,
                                 @RequestBody String title){
        return sectionService.createSection(courseId, secNo, title);
    }

    @ApiOperation("创建小节")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId",value = "课程id",paramType = "path"),
            @ApiImplicitParam(name = "secId",value = "章节id",paramType = "path"),
            @ApiImplicitParam(name = "branchNo",value = "插入的小节的前一小节的序号",paramType = "param")
    })
    @PostMapping("/{secId}/append")
    public SectionBranches createBranch(@PathVariable("courseId")Long courseId,
                                        @PathVariable("secId")int secId,
                                        @RequestParam("branchNo")Integer branchNo,
                                        @RequestBody TitleAndDes form) {
        return sectionService.createBranch(courseId, secId,branchNo, form);
    }


    @ApiOperation("发布试卷/作业")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "课程id",name = "courseId",paramType = "path"),
            @ApiImplicitParam(value = "章节序号",name = "secNo",paramType = "path"),
            @ApiImplicitParam(value = "小节序号",name = "branchNo",paramType = "path"),
            @ApiImplicitParam(value = "试卷id",name = "paperId",paramType = "param")
    })
    @PostMapping("/{secNo}/{branchNo}/papers/issue")
    public Section issuePaper(@PathVariable("courseId")Long courseId,
                              @PathVariable("secNo")int secNo,
                              @PathVariable("branchNo")int branchNo,
                              @RequestParam("paperId")Long paperId)throws Exception{
        return sectionService.issuePaper(courseId, secNo, branchNo,paperId);
    }

    @ApiOperation("发布资源")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "课程id",name = "courseId",paramType = "path"),
            @ApiImplicitParam(value = "章节序号",name = "secNo",paramType = "path"),
            @ApiImplicitParam(value = "小节序号",name = "branchNo",paramType = "path"),
            @ApiImplicitParam(value = "资源id",name = "resourceId",paramType = "param")
    })
    @PostMapping("/{secNo}/{branchNo}/resources/issue")
    public Section issueResources(@PathVariable("courseId")Long courseId,
                                  @PathVariable("secNo")int secNo,
                                  @PathVariable("branchNo")int branchNo,
                                  @RequestParam("resourceId")Long resourceId){
        return sectionService.issueResource(courseId, secNo, branchNo, resourceId);
    }
}
