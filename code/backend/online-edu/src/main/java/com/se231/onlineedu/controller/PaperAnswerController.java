package com.se231.onlineedu.controller;

import java.util.List;
import com.se231.onlineedu.message.request.SubmitAnswerForm;
import com.se231.onlineedu.model.PaperAnswer;
import com.se231.onlineedu.security.services.UserPrinciple;
import com.se231.onlineedu.service.PaperAnswerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zhe Li
 * @date 2019/07/10
 */
@RestController
@Api(tags = "学生完成作业的控制类")
@RequestMapping("/api/courses/{courseId}/papers/{paperId}/answer")
public class PaperAnswerController {

    @Autowired
    PaperAnswerService paperAnswerService;

    @ApiOperation("学生提交作业")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "课程id",name="courseId",paramType = "path"),
            @ApiImplicitParam(value = "作业id",name="paperId",paramType = "path")
    })
    @PostMapping
    public PaperAnswer submitAnswer(@PathVariable("courseId")Long courseId,
                                                    @PathVariable("paperId")Long paperId,
                                                    @AuthenticationPrincipal UserPrinciple userPrinciple,
                                                    @RequestBody SubmitAnswerForm form)throws Exception{

        return paperAnswerService.submitAnswer(userPrinciple.getId(),courseId,paperId,form);
    }

    @GetMapping
    public List<PaperAnswer> getPersonalPaperAnswer(@PathVariable("paperId")Long paperId,
                                                    @AuthenticationPrincipal UserPrinciple userPrinciple){
        return paperAnswerService.getPersonalPaperAnswer(paperId,userPrinciple.getId());
    }

}
