package com.se231.onlineedu.controller;

import javax.validation.Valid;
import com.se231.onlineedu.message.request.PaperForm;
import com.se231.onlineedu.model.Paper;
import com.se231.onlineedu.model.Section;
import com.se231.onlineedu.service.PaperService;
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
@RestController
@RequestMapping("api/courses/{id}/papers")
public class PaperController {

    @Autowired
    private PaperService paperService;

    @PostMapping
    public ResponseEntity<Paper> addNewPaper(@PathVariable("id")Long courseId,
                                             @Valid @RequestBody PaperForm form)throws Exception{
        return ResponseEntity.ok(paperService.addNewPaper(form.getQuestionFormList(),courseId,
                form.getStart(),form.getEnd()));
    }

    @PostMapping("/{paperId}/issue")
    public ResponseEntity<Section> issuePaper(@PathVariable("id")Long courseId,
                                              @RequestParam("section")int secNo,
                                              @PathVariable("paperId")Long paperId)throws Exception{
        return ResponseEntity.ok(paperService.issuePaper(courseId, secNo, paperId));
    }

}
