package com.se231.onlineedu.Repository;

import com.se231.onlineedu.model.Course;
import com.se231.onlineedu.model.User;
import com.se231.onlineedu.repository.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Zhe Li
 * @date 2019/07/22
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class PaperAnswerRepositoryTest {
    @Autowired
    PaperAnswerRepository paperAnswerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    PaperRepository paperRepository;

    @Autowired
    QuestionRepository questionRepository;

//    @Before
//    public void setup(){
//        User user = new User();
//        user.setUsername("admin");
//        userRepository.save(user);
//        Course course = new Course();
//        course.setCourseTitle("shit");
//        courseRepository.save(course);
//
//    }
//
//    @Test
//    public void getMaxTimesTest(){
//    }
}
