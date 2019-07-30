package com.se231.onlineedu.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.se231.onlineedu.BaseTest;
import com.se231.onlineedu.message.request.QuestionAnswer;
import com.se231.onlineedu.message.request.SubmitAnswerForm;
import com.se231.onlineedu.model.PaperAnswer;
import com.se231.onlineedu.model.PaperAnswerPrimaryKey;
import com.se231.onlineedu.model.PaperAnswerState;
import com.se231.onlineedu.repository.AnswerRepository;
import com.se231.onlineedu.repository.PaperAnswerRepository;
import com.se231.onlineedu.repository.PaperRepository;
import com.se231.onlineedu.repository.QuestionRepository;
import com.se231.onlineedu.service.CourseService;
import com.se231.onlineedu.service.PaperAnswerService;
import com.se231.onlineedu.service.UserService;
import com.se231.onlineedu.serviceimpl.PaperAnswerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author Zhe Li
 * @date 2019/07/30
 */
@RunWith(SpringRunner.class)
public class PaperServiceImplTest extends BaseTest {
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

    @Test(expected = RuntimeException.class)
    public void answerTimesTest()throws Exception{
        Mockito.when(paperAnswerRepository.getMaxTimes(user.getId(),paper.getId())).thenReturn(Optional.of(3));
        PaperAnswerPrimaryKey paperAnswerPrimaryKey = new PaperAnswerPrimaryKey(user,paper,3);
        PaperAnswer paperAnswer = new PaperAnswer(paperAnswerPrimaryKey);
        paperAnswer.setState(PaperAnswerState.FINISHED);
        Mockito.when(paperAnswerRepository.findById(paperAnswerPrimaryKey)).thenReturn(Optional.of(paperAnswer));

        QuestionAnswer questionAnswer = new QuestionAnswer(question.getId(),"A");
        List<QuestionAnswer> questionAnswerList = new ArrayList<>();
        questionAnswerList.add(questionAnswer);
        SubmitAnswerForm submitAnswerForm = new SubmitAnswerForm(questionAnswerList,"FINISHED");

        paperAnswerService.submitAnswer(user.getId(),course.getId(),paper.getId(),submitAnswerForm);
    }
}
