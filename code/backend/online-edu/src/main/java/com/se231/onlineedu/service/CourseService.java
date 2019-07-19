package com.se231.onlineedu.service;

import java.util.List;

import com.se231.onlineedu.message.request.CourseApplicationForm;
import com.se231.onlineedu.message.request.SignInCourseForm;
import com.se231.onlineedu.message.response.CourseWithIdentity;
import com.se231.onlineedu.model.Course;
import com.se231.onlineedu.model.Learn;
import com.se231.onlineedu.model.User;
import org.springframework.http.ResponseEntity;

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
     * @param form information of course
     * @param userId    the id of applicant
     * @return  generated course entity
     *
     */
    Course applyToStartCourse(CourseApplicationForm form, Long prototypeId, Long userId);

    /**
     * this service allow a admin or super admin to examine a start course application.
     * @param courseId  the id of applied course.
     * @param decision   the decision of admin
     * @return  the applied course
     *
     */
    Course examineStartCourseApplication(Long courseId,String decision);

    /**
     * this service will allow a user to pick a existing course.
     * @param userId    the id of applicant
     * @param courseId  the id of picked course
     * @return  applicant's course list
     */
    Learn pickCourse(Long userId, Long courseId);

    /**
     * this service allow a teacher or admin to get the list of students who have picked this course
     * @param courseId  id of the course
     * @return  the set of students
     *
     */
    List<User> getStudentsList(Long courseId);

    /**
     * this service allow user to get information of a specific course.
     * @param courseId  id of the required course
     * @return  the information of course
     *
     */
    Course getCourseInfo(Long courseId);

    /**
     * this service allow user to get all courses.
     * @return  the list of all course
     */
    List<Course> getAllCourse();

    /**
     * @param avatarUrl
     * @param id
     * @return
     */
    Course updateCourseAvatar(String avatarUrl, Long id);

    List<String> getTeacherAssistantAndTeacherEmail(Long id);
    /**
     * this service check whether the specific user has picked the course or not
     * @param courseId  the checked course
     * @param userId    the request user
     * @return  true if the user has picked the course.
     * @throws Exception    mainly not found exception
     */
    Boolean checkIfUserPick(Long courseId,Long userId);

    /**
     * this service allow the teacher of a course or admin to modify information of the course
     * @param courseId    id of course.
     * @param form  form of course information.
     * @return  course info after modify
     *
     */
    Course modifyCourseInfo(Long courseId,CourseApplicationForm form);

    /**
     * @param id
     * @param signInForm
     * @return
     */
    Course saveSignIn(Long id, SignInCourseForm signInForm);

    /**
     *this service mainly used to automatically set state of course.
     * @param courseId  id of changed course
     * @param state state after change.
     */
    Course setState(Long courseId,String state);

    /**
     * @param courseId
     * @param userId
     * @return
     */
    CourseWithIdentity getCourseInfoWithIdentity(Long courseId, Long userId);

    /**
     * @param id
     * @param userId
     * @return
     */
    Course selectTeacherAssistant(Long id, Long userId);
}
