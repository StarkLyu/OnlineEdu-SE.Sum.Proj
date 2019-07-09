package com.se231.onlineedu.controller;

import javax.validation.Valid;
import com.se231.onlineedu.message.request.SubmitQuestionForm;
import com.se231.onlineedu.model.Question;
import com.se231.onlineedu.model.QuestionType;
import com.se231.onlineedu.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Question Controller Class
 *
 * controller of the api related to questions(based a existing course prototype.
 *
 * @author Zhe Li
 *
 * @date 2019/7/5
 */
@RestController
@RequestMapping("/api/coursePrototype/{id}/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @PostMapping
    @PreAuthorize("hasAnyRole('TEACHING_ADMIN','ADMIN','SUPER_ADMIN')")
    public ResponseEntity<Question> submitQuestion(@Valid @RequestBody SubmitQuestionForm form,
                                                   @PathVariable("id")Long coursePrototypeId)throws Exception{
        return ResponseEntity.ok(questionService.submitQuestion(coursePrototypeId,
                QuestionType.valueOf(form.getQuestionType().toUpperCase()),form.getQuestion(),form.getAnswer()));
    }

}
