package com.se231.onlineedu.service;

import com.se231.onlineedu.message.request.TempRecord;
import com.se231.onlineedu.model.StudyReport;
import com.se231.onlineedu.model.StudyTempRecord;

/**
 * @author Zhe Li
 * @date 2019/09/06
 */
public interface StudyRecordService {
    StudyTempRecord submitRecord(Long userId, TempRecord tempRecord);

    StudyReport getReport(Long userId);
}
