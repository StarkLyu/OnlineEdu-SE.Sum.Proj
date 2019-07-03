package com.se231.onlineedu.service;

import com.se231.onlineedu.message.request.CreateCourseApplicationForm;
import com.se231.onlineedu.message.response.ApplyResponse;
import com.se231.onlineedu.model.ApplyPrimaryKey;
import com.se231.onlineedu.model.User;


/**
 * Interface of courses' service
 *
 * Support service for courses.
 *
 * @author Zhe Li
 *
 * @date 2019/7/3
 */

public interface CourseService {

    /**
     * Teaching Admin(or higher)could apply to create a new course prototype.
     *
     * @param user the applicant
     * @param form create application form
     * @return response for front
     */
    ApplyResponse createCourse(CreateCourseApplicationForm form, User user) throws Exception;

    /**
     * Teaching Admin could apply for the usage of a course prototype.
     * @param courseId  the applying course
     * @param user the applicant
     * @return  response for front
     */
    ApplyResponse applyForCourse(Long courseId,User user) throws Exception;

    /**
     * Admin approve the application of create course.
     *
     * @param coursePrototypeId id of the application of creating a course
     * @param decision -1 represent disapproval while 1 represent approval
     */
    void decideCreateCourse(Long coursePrototypeId,int decision)throws Exception;

    /**
     * Admin approve the application of using a course
     * @param applyPrimaryKey id of the application of using a course
     * @param decision -1 represent disapproval while 1 represent approval
     */
    void decideUseCourse(Long courseId,Long applicantId,int decision)throws Exception;
}
