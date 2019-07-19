package com.se231.onlineedu.service;

import com.se231.onlineedu.message.request.SubmitAnswerForm;
import com.se231.onlineedu.model.PaperAnswer;

/**
 * Paper Answer Service Interface
 *
 * interface of service related to students answer papers.
 *
 * @author Zhe Li
 *
 * @date 2019/07/10
 */
public interface PaperAnswerService {
    /**
     * this service allow students to answer a paper ,then record their answer and mark a score.
     * @param userId    id of user who answer the paper
     * @param courseId  id of the course
     * @param paperId   id of the answered paper
     * @param form   a form contains answer and state.
     * @return  the paper with answers the user just submit.
     * @throws Exception    mainly not found exception.
     */
    PaperAnswer submitAnswer(Long userId, Long courseId, Long paperId, SubmitAnswerForm form)throws Exception;

    /**
     * this service will mark all answer of a specific paper.
     * @param paperId
     */
    void autoMark(Long paperId);
}
