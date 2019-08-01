package com.se231.onlineedu.Service;

import com.se231.onlineedu.model.Paper;
import com.se231.onlineedu.model.Question;
import com.se231.onlineedu.repository.*;
import com.se231.onlineedu.service.CourseService;
import com.se231.onlineedu.service.PaperService;
import com.se231.onlineedu.serviceimpl.PaperServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Zhe Li
 * @date 2019/07/31
 */
@RunWith(SpringRunner.class)
public class PaperServiceTest {
    @TestConfiguration
    static class PaperServiceImplTestContextConfig{
        @Bean
        public PaperService paperService(){
            return new PaperServiceImpl();
        }
    }

    @Autowired
    PaperService paperService;

    @MockBean
    private PaperWithQuestionsRepository paperWithQuestionsRepository;

    @MockBean
    private QuestionRepository questionRepository;

    @MockBean
    private PaperRepository paperRepository;

    @MockBean
    private CourseService courseService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PaperAnswerRepository paperAnswerRepository;

    private static Paper paper;

    private static Question question;

    @Test
    public void addNewPaperTest(){
        //initialize paper info
        Paper paper2 = new Paper();
        paper2.setId(2L);

        Question question = new Question();
        question.setId(2L);
    }
}
