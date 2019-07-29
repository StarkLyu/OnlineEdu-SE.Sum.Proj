package com.se231.onlineedu.service;

import java.util.List;
import com.se231.onlineedu.message.request.PaperForm;
import com.se231.onlineedu.message.response.PaperFinish;
import com.se231.onlineedu.model.Paper;
import com.se231.onlineedu.model.Section;

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
     * @param courseId  id of the course
     * @param form form of add paper request
     * @return  the generated paper.
     * @throws Exception    mainly contains not found exception
     */
    Paper addNewPaper(PaperForm form, Long courseId)throws Exception;

    /**
     * this service allow student to get his process of paper in this course.
     * @param userId id of user
     * @param courseId  id of course
     * @return  paper finish state class
     */
    List<PaperFinish> getPaperFinish(Long userId, Long courseId);

    /**
     * this service allow teacher to get all students' finish state of a specific paper
     * @param courseId  id of course
     * @param paperId   id of paper
     * @return  finish state list
     */
    List<PaperFinish> getStudentFinish(Long courseId,Long paperId);


}
