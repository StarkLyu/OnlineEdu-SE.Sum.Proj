package com.se231.onlineedu.service;

import com.se231.onlineedu.message.request.PaperForm;
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

}
