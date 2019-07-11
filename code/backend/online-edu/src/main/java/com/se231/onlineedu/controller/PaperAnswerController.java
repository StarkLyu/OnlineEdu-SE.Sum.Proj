package com.se231.onlineedu.controller;

import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
import com.se231.onlineedu.model.PaperAnswer;
import com.se231.onlineedu.security.services.UserPrinciple;
import com.se231.onlineedu.service.PaperAnswerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zhe Li
 * @date 2019/07/10
 */
@RestController
@Api
@RequestMapping("/api/courses/{courseId}/papers/{paperId}/answer")
public class PaperAnswerController {

    @Autowired
    PaperAnswerService paperAnswerService;
    @PostMapping
    public ResponseEntity<PaperAnswer> submitAnswer(@PathVariable("courseId")Long courseId,
                                                    @PathVariable("paperId")Long paperId,
                                                    @AuthenticationPrincipal UserPrinciple userPrinciple,
                                                    @RequestBody JSONObject jsonObject)throws Exception{
        Map<Long,String> map = new HashMap<>(10);
        for(Map.Entry<String,Object> entry:jsonObject.entrySet()){
            Long questionId=Long.parseLong(entry.getKey());
            String answer =(String) entry.getValue();
            map.put(questionId,answer);
        }
        return ResponseEntity.ok(paperAnswerService.submitAnswer(userPrinciple.getId(),courseId,paperId,map));
    }

}
