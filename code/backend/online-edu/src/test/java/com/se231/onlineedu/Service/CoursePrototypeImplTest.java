package com.se231.onlineedu.Service;

import com.se231.onlineedu.message.request.TitleAndDes;
import com.se231.onlineedu.model.Apply;
import com.se231.onlineedu.model.CoursePrototype;
import com.se231.onlineedu.model.User;
import com.se231.onlineedu.repository.ApplyRepository;
import com.se231.onlineedu.repository.CoursePrototypeRepository;
import com.se231.onlineedu.repository.ResourceRepository;
import com.se231.onlineedu.service.CoursePrototypeService;
import com.se231.onlineedu.service.UserService;
import com.se231.onlineedu.serviceimpl.CoursePrototypeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class CoursePrototypeImplTest {

    @TestConfiguration
    static class CoursePrototypeServiceImplTestContextConfig{
        @Bean
        public CoursePrototypeService authService(){
            return new CoursePrototypeServiceImpl();
        }

    }

    @Autowired
    private CoursePrototypeService coursePrototypeService;

    @MockBean
    private UserService userService;


    @MockBean
    private CoursePrototypeRepository coursePrototypeRepository;

    @MockBean
    private ApplyRepository applyRepository;

    @MockBean
    private ResourceRepository resourceRepository;


    @Before
    public void setup(){
        Mockito.when(coursePrototypeRepository.save(any(CoursePrototype.class))).thenAnswer(i -> i.getArguments()[0]);
        Mockito.when(applyRepository.save(any(Apply.class))).thenAnswer(i -> i.getArguments()[0]);

    }

    @Test
    public void createCourse(){
        TitleAndDes form = new TitleAndDes();
        form.setDescription("des");
        form.setTitle("123");


        User user = new User();
        user.setId(1L);
        Mockito.when(userService.getUserInfo(1L)).thenReturn(user);

        CoursePrototype coursePrototype = coursePrototypeService.createCourse(form, 1L);
        assertThat(coursePrototype.getUsers().size()).isEqualTo(1L);
        assertThat(coursePrototype.getDescription()).isEqualTo("des");
    }

    @Test
    public void applyForCourse(){
        CoursePrototype coursePrototype = new CoursePrototype();
        coursePrototype.setId(1L);
        Optional<CoursePrototype> coursePrototypeOptional = Optional.of(coursePrototype);

        User user = new User();
        user.setId(1L);
        Mockito.when(userService.getUserInfo(1L)).thenReturn(user);
        Mockito.when(coursePrototypeRepository.findById(1L)).thenReturn(coursePrototypeOptional);
        Apply apply = coursePrototypeService.applyForCourse(1L,1L);
        assertThat(apply.getApplicationForCoursePK().getCoursePrototype().getId()).isEqualTo(1L);
    }
}
