package com.se231.onlineedu.service;

import java.util.Date;
import java.util.List;
import com.se231.onlineedu.message.request.PaperQuestionForm;
import com.se231.onlineedu.model.Paper;

/**
 * Service Interface related to paper.
 *
 * this interface contains all the interface of service related to a specific paper.
 *
 * @author Zhe Li
 *
 * @date 2019/7/5
 */
public interface PaperService {

    /**
     * this service allowed teacher of a specific course to add a new paper into the course.
     * @param paperQuestionFormsList    the questions of paper.
     * @param courseId  id of the course
     * @param start the beginning time of the paper
     * @param end the end time of the paper
     * @return  the generated paper.
     * @throws Exception    mainly contains not found exception
     */
    Paper addNewPaper(List<PaperQuestionForm> paperQuestionFormsList, Long courseId,
                      Date start, Date end)throws Exception;
}
