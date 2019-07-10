package com.se231.onlineedu.controller;

import javax.validation.Valid;

import com.alibaba.fastjson.JSONObject;
import com.se231.onlineedu.message.request.SubmitQuestionForm;
import com.se231.onlineedu.model.Question;
import com.se231.onlineedu.model.QuestionType;
import com.se231.onlineedu.service.QuestionService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * Question Controller Class
 * <p>
 * controller of the api related to questions(based a existing course prototype.
 *
 * @author Zhe Li
 * @date 2019/7/5
 */
@RestController
@RequestMapping("/api/coursePrototypes/{id}/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @PostMapping
    @PreAuthorize("hasAnyRole('TEACHING_ADMIN','ADMIN','SUPER_ADMIN')")
    public ResponseEntity<Question> submitQuestion(@RequestBody JSONObject questionJSON,
                                                   @PathVariable("id") Long coursePrototypeId) throws Exception {
        String type = ((String) questionJSON.get("type")).toUpperCase();
        StringBuilder questionBuilder = new StringBuilder();
        questionBuilder.append((String) questionJSON.get("content"));
        List<String> options = (List<String>) questionJSON.get("options");
        for (String option : options) {
            questionBuilder.append("\r\n" + option);
        }
        String question = questionBuilder.toString();

        String answer = (String) questionJSON.get("answer");
        return ResponseEntity.ok(questionService.submitQuestion(coursePrototypeId,
                QuestionType.valueOf(type), question, answer));
    }

//    @PostMapping("/{questionId}")
//    @PreAuthorize("hasAnyRole('TEACHING_ADMIN','ADMIN','SUPER_ADMIN')")
//    public ResponseEntity<Question> submitQuestionImage(@PathVariable("questionId") Long questionId,
//                                                        @PathVariable("id") Long coursePrototypeId, @RequestParam("file") MultipartFile[] files) throws Exception {
//        for (MultipartFile multipartFile : files) {
//            if (multipartFile.getSize() > limit) {
//                return ResponseEntity.badRequest().body("exceeded max size");
//            }
//
//            String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
//
//            if (!fileExtension.contains(suffix)) {
//                return ResponseEntity.badRequest().body("file format not supported");
//            }
//            String fileName = nginxPath + id + "-avatar/" + id + "-avatar" + suffix;
//            File file = new File(fileName);
//
//            if (file.getParentFile().exists()) {
//                FileUtils.cleanDirectory(file.getParentFile());
//            } else {
//                file.getParentFile().mkdir();
//            }
//            file.createNewFile();
//            multipartFile.transferTo(file);
//
//            return ResponseEntity.ok(userService.updateUserAvatar(id + "-avatar/" + id + "-avatar" + suffix, id));
//        }
//    }


}
