package com.se231.onlineedu.Service;

import java.util.Optional;
import com.se231.onlineedu.exception.NotFoundException;
import com.se231.onlineedu.model.Question;
import com.se231.onlineedu.repository.QuestionRepository;
import com.se231.onlineedu.service.CoursePrototypeService;
import com.se231.onlineedu.service.QuestionService;
import com.se231.onlineedu.serviceimpl.QuestionServiceImpl;
import org.junit.Assert;
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
 * @date 2019/08/01
 */
@RunWith(SpringRunner.class)
public class QuestionServiceImplTest {
    @TestConfiguration
    static class QuestionServiceImplTestContextConfig{
        @Bean
        public QuestionService questionService(){
            return new QuestionServiceImpl();
        }
    }

    @Autowired
    QuestionService questionService;

    @MockBean
    private QuestionRepository questionRepository;

    @MockBean
    private CoursePrototypeService coursePrototypeService;

    @Test(expected = NotFoundException.class)
    public void questionNotFoundTest() {
        Mockito.when(questionRepository.findById(1L)).thenReturn(Optional.empty());
        questionService.getQuestionInfo(1L);
    }

    @Test
    public void getQuestionTest(){
        Question question = new Question();
        question.setId(1L);
        question.setQuestion("test");
        Mockito.when(questionRepository.findById(1L)).thenReturn(Optional.of(question));

        Question question1 = questionService.getQuestionInfo(1L);
        
    }
}
