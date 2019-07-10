package com.se231.onlineedu.repository;

import com.se231.onlineedu.model.PaperAnswer;
import com.se231.onlineedu.model.PaperAnswerPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Paper Answer Repository
 *
 * used to operate with database
 *
 * @author Zhe Li
 *
 * @date 2019/07/10
 */
public interface PaperAnswerRepository extends JpaRepository<PaperAnswer, PaperAnswerPrimaryKey> {
}
