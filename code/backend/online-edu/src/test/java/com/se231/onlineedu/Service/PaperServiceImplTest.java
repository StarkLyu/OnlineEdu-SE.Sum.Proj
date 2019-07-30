package com.se231.onlineedu.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.se231.onlineedu.exception.NotFoundException;
import com.se231.onlineedu.exception.ValidationException;
import com.se231.onlineedu.message.request.QuestionAnswer;
import com.se231.onlineedu.message.request.SubmitAnswerForm;
import com.se231.onlineedu.model.*;
import com.se231.onlineedu.repository.AnswerRepository;
import com.se231.onlineedu.repository.PaperAnswerRepository;
import com.se231.onlineedu.repository.PaperRepository;
import com.se231.onlineedu.repository.QuestionRepository;
import com.se231.onlineedu.service.CourseService;
import com.se231.onlineedu.service.PaperAnswerService;
import com.se231.onlineedu.service.UserService;
import com.se231.onlineedu.serviceimpl.PaperAnswerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Zhe Li
 * @date 2019/07/30
 */
@RunWith(SpringRunner.class)
public class PaperServiceImplTest{
    @TestConfiguration
    static class UserServiceImplTestContextConfig{
        @Bean
        public PaperAnswerService paperAnswerService(){
            return new PaperAnswerServiceImpl();
        }
    }

    @Autowired
    PaperAnswerService paperAnswerService;

    @MockBean
    UserService userService;

    @MockBean
    CourseService courseService;

    @MockBean
    PaperRepository paperRepository;

    @MockBean
    QuestionRepository questionRepository;

    @MockBean
    AnswerRepository answerRepository;

    @MockBean
    PaperAnswerRepository paperAnswerRepository;

    private static User user;

    private static Paper paper;

    private static Question question;

    private static Course course;

    private static SubmitAnswerForm submitAnswerForm;

    private static List<QuestionAnswer> questionAnswers;

    @Before
    public void setup(){
        user = new User();
        user.setId(1L);

        paper = new Paper();
        paper.setId(1L);

        question = new Question();
        question.setId(1L);

        course = new Course();
        course.setId(1L);

        paper.setCourse(course);

        QuestionAnswer questionAnswer = new QuestionAnswer(question.getId(),"A");
        List<QuestionAnswer> questionAnswers = new ArrayList<>();
        questionAnswers.add(questionAnswer);
        submitAnswerForm = new SubmitAnswerForm(questionAnswers,"FINISHED");

    }

    @Test(expected = RuntimeException.class)
    public void answerTimesTest()throws Exception{
        Mockito.when(paperAnswerRepository.getMaxTimes(user.getId(),paper.getId())).thenReturn(Optional.of(3));
        PaperAnswerPrimaryKey paperAnswerPrimaryKey = new PaperAnswerPrimaryKey(user,paper,3);
        PaperAnswer paperAnswer = new PaperAnswer(paperAnswerPrimaryKey);
        paperAnswer.setState(PaperAnswerState.FINISHED);
        Mockito.when(paperAnswerRepository.findById(paperAnswerPrimaryKey)).thenReturn(Optional.of(paperAnswer));
        paperAnswerService.submitAnswer(user.getId(),course.getId(),paper.getId(),submitAnswerForm);
    }

    @Test
    public void submitAnswer()throws Exception{
        Mockito.when(paperAnswerRepository.getMaxTimes(1L,1L)).thenReturn(Optional.empty());
        Mockito.when(paperAnswerRepository.save(any(PaperAnswer.class))).thenAnswer(i -> i.getArguments()[0]);
        Mockito.when(paperRepository.findById(1L)).thenReturn(Optional.of(paper));
        Mockito.when(userService.getUserInfo(1L)).thenReturn(user);
        Mockito.when(courseService.getCourseInfo(1L)).thenReturn(course);
        Mockito.when(questionRepository.findById(1L)).thenReturn(Optional.of(question));

        PaperAnswer paperAnswer = paperAnswerService.submitAnswer(1L,1L,1L,submitAnswerForm);
        assertThat(paperAnswer.getPaperAnswerPrimaryKey().getTimes()).isEqualTo(1);
    }

    @Test(expected = NotFoundException.class)
    public void paperNotFoundTest()throws Exception{
        Mockito.when(userService.getUserInfo(1L)).thenReturn(user);
        Mockito.when(courseService.getCourseInfo(1L)).thenReturn(course);
        Mockito.when(paperRepository.findById(2L)).thenReturn(Optional.empty());

        paperAnswerService.submitAnswer(1L,1L,2L,submitAnswerForm);
    }

    @Test(expected = ValidationException.class)
    public void notMatchTest()throws Exception{
        Mockito.when(userService.getUserInfo(1L)).thenReturn(user);
        Mockito.when(courseService.getCourseInfo(1L)).thenReturn(course);
        Paper paper2 = new Paper();
        paper2.setId(2L);
        Course course2 = new Course();
        course2.setId(2L);
        paper2.setCourse(course2);
        Mockito.when(paperRepository.findById(2L)).thenReturn(Optional.of(paper2));

        paperAnswerService.submitAnswer(1L,1L,2L,submitAnswerForm);
    }


}
