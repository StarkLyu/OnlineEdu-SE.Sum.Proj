package com.se231.onlineedu.repository;

import java.util.Optional;
import com.se231.onlineedu.model.PaperAnswer;
import com.se231.onlineedu.model.PaperAnswerPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
    /**
     * this function helps to get the max answer times of a user and a paper.
     * @param user_id   id of user
     * @param paper_id  id of paper
     * @return  times of answer.
     */
    @Query(value = "select max(times) from paper_answer where user_id = ?1 and paper_id=?2 ",nativeQuery = true)
    Optional<Integer> getMaxTimes(Long user_id,Long paper_id);
}
