package com.se231.onlineedu.controller;

import javax.validation.Valid;
import com.se231.onlineedu.message.request.PaperForm;
import com.se231.onlineedu.model.Paper;
import com.se231.onlineedu.model.Section;
import com.se231.onlineedu.service.PaperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Paper Controller Class
 *
 * controller of the api related to papers(based a existing course).
 *
 * @author Zhe Li
 *
 * @date 2019/7/5
 */
@Api(tags = "与作业，试卷相关的控制类")
@RestController
@RequestMapping("api/courses/{id}/papers")
public class PaperController {

    @Autowired
    private PaperService paperService;

    @ApiOperation("增加新试卷")
    @ApiImplicitParam(name = "id",value = "课程的id",paramType = "path")
    @PostMapping
    public ResponseEntity<Paper> addNewPaper(@PathVariable("id")Long courseId,
                                             @Valid @RequestBody PaperForm form)throws Exception{
        return ResponseEntity.ok(paperService.addNewPaper(form,courseId));
    }

    @PostMapping("/{paperId}/issue")
    public Section issuePaper(@PathVariable("id")Long courseId,
                                              @RequestParam("section")int secNo,
                                              @PathVariable("paperId")Long paperId)throws Exception{
        return paperService.issuePaper(courseId, secNo, paperId);
    }
}
