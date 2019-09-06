package com.se231.onlineedu.repository;

import com.se231.onlineedu.model.StudyRecord;
import com.se231.onlineedu.model.StudyRecordPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Zhe Li
 * @date 2019/09/06
 */
public interface StudyRecordRepository extends JpaRepository<StudyRecord, StudyRecordPrimaryKey> {
}
