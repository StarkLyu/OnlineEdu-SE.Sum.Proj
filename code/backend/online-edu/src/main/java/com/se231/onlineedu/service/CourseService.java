package com.se231.onlineedu.service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import com.se231.onlineedu.model.Course;
import com.se231.onlineedu.model.User;

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
     * @throws Exception mainly throw not found exception
     */
    Course applyToStartCourse(Long prototypeId, Date startDate, Date endDate, Long userId) throws Exception;

    /**
     * this service allow a admin or super admin to examine a start course application.
     * @param courseId  the id of applied course.
     * @param decision   the decision of admin
     * @return  the applied course
     * @throws Exception exception mainly contains not found exception
     */
    Course examineStartCourseApplication(Long courseId,String decision)throws Exception;

    /**
     * this service will allow a user to pick a existing course.
     * @param userId    the id of applicant
     * @param courseId  the id of picked course
     * @return  applicant's course list
     * @throws Exception mainly throws not found exception
     */
    List<Course> pickCourse(Long userId,Long courseId)throws Exception;

    /**
     * this service allow a teacher or admin to get the list of students who have picked this course
     * @param courseId  id of the course
     * @return  the set of students
     * @throws Exception mainly throw not found exception
     */
    Set<User> getStudentsList(Long courseId)throws Exception;

    /**
     * this service allow user to get information of a specific course.
     * @param courseId  id of the required course
     * @return  the information of course
     * @throws Exception    mainly throw not found exception
     */
    Course getCourseInfo(Long courseId)throws Exception;

    /**
     * this service allow user to get all courses.
     * @return  the list of all course
     */
    List<Course> getAllCourse();

    Course updateCourseAvatar(String avatarUrl, Long id) throws Exception;

    List<String> getTAAndTeacherEmail(Long id) throws Exception;
}
