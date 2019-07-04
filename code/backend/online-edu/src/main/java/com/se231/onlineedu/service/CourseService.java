package com.se231.onlineedu.service;

import java.util.Date;
import com.se231.onlineedu.model.Course;

/**
 * Course Service Interface
 *
 * interface of service related to course
 *
 * @author Zhe Li
 * @date 2019/7/4
 */
public interface CourseService {

    /**
     * this service will allow a teaching admin(or higher priority)apply to start a course from a existing course prototype
     * @param prototypeId the prototype of applied course
     * @param startDate the beginning date of the course
     * @param endDate the end date of the course
     * @param userId    the id of applicant
     * @return  generated course entity
     */
    Course applyToStartCourse(Long prototypeId, Date startDate, Date endDate, Long userId) throws Exception;
}
