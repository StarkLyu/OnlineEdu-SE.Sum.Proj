package com.se231.onlineedu.controller;

import com.se231.onlineedu.message.request.TempRecord;
import com.se231.onlineedu.model.StudyTempRecord;
import com.se231.onlineedu.security.services.UserPrinciple;
import com.se231.onlineedu.service.StudyRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zhe Li
 * @date 2019/09/06
 */
@Api("上传与生成学习记录的相关接口")
@RestController
@RequestMapping("/api/studyRecord")
public class StudyRecordController {

    @Autowired
    StudyRecordService studyRecordService;

    @ApiOperation("上传看视频记录")
    @PostMapping("/submit")
    public StudyTempRecord submitRecord(@RequestBody TempRecord tempRecord,
                                        @AuthenticationPrincipal UserPrinciple userPrinciple){
        return studyRecordService.submitRecord(userPrinciple.getId(),tempRecord);
    }
}
