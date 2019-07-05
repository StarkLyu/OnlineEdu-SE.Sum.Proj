package com.se231.onlineedu.repository;

import com.se231.onlineedu.model.PaperWithQuestions;
import com.se231.onlineedu.model.PaperWithQuestionsPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Paper With Questions Repository Interface
 * the interface used to operate with database
 *
 * @author Zhe Li
 *
 * @date 2019/7/5
 */
public interface PaperWithQuestionsRepository extends JpaRepository<PaperWithQuestions,PaperWithQuestionsPrimaryKey> {

}
