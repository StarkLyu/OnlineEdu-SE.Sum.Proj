package com.se231.onlineedu.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import com.se231.onlineedu.exception.NotFoundException;
import com.se231.onlineedu.exception.NotMatchException;
import com.se231.onlineedu.exception.ValidationException;
import com.se231.onlineedu.message.request.MarkForm;
import com.se231.onlineedu.message.request.QuestionAnswer;
import com.se231.onlineedu.message.request.SubmitAnswerForm;
import com.se231.onlineedu.model.*;
import com.se231.onlineedu.repository.AnswerRepository;
import com.se231.onlineedu.repository.PaperAnswerRepository;
import com.se231.onlineedu.repository.PaperRepository;
import com.se231.onlineedu.repository.QuestionRepository;
import com.se231.onlineedu.service.CourseService;
import com.se231.onlineedu.service.PaperAnswerService;
import com.se231.onlineedu.service.PaperService;
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
public class PaperAnswerServiceImplTest {
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
    PaperService paperService;

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

        PaperWithQuestions paperWithQuestions = new PaperWithQuestions(paper,question,1,1.0);
        paper.setQuestions(List.of(paperWithQuestions));
    }

    @Test(expected = ValidationException.class)
    public void answerTimesTest()throws Exception{
        Mockito.when(paperAnswerRepository.getMaxTimes(user.getId(),paper.getId())).thenReturn(Optional.of(3));
        Mockito.when(paperService.getPaperInfo(1L,1L)).thenReturn(paper);
        Mockito.when(userService.getUserInfo(1L)).thenReturn(user);
        Mockito.when(courseService.getCourseInfo(1L)).thenReturn(course);
        PaperAnswerPrimaryKey paperAnswerPrimaryKey = new PaperAnswerPrimaryKey(user,paper,3);
        PaperAnswer paperAnswer = new PaperAnswer(paperAnswerPrimaryKey);
        paperAnswer.setState(PaperAnswerState.FINISHED);
        Mockito.when(paperAnswerRepository.getOne(paperAnswerPrimaryKey)).thenReturn(paperAnswer);
        paperAnswerService.submitAnswer(user.getId(),course.getId(),paper.getId(),submitAnswerForm);
    }

    @Test
    public void submitAnswer()throws Exception{
        Mockito.when(paperAnswerRepository.getMaxTimes(1L,1L)).thenReturn(Optional.empty());
        Mockito.when(paperAnswerRepository.save(any(PaperAnswer.class))).thenAnswer(i -> i.getArguments()[0]);
        Mockito.when(paperService.getPaperInfo(1L,1L)).thenReturn(paper);
        Mockito.when(userService.getUserInfo(1L)).thenReturn(user);
        Mockito.when(courseService.getCourseInfo(1L)).thenReturn(course);
        Mockito.when(questionRepository.findById(1L)).thenReturn(Optional.of(question));
        Mockito.when(paperRepository.getOne(1L)).thenReturn(paper);

        PaperAnswer paperAnswer = paperAnswerService.submitAnswer(1L,1L,1L,submitAnswerForm);
        assertThat(paperAnswer.getPaperAnswerPrimaryKey().getTimes()).isEqualTo(1);
    }

    @Test
    public void notFinishTest()throws Exception{
        Mockito.when(paperAnswerRepository.getMaxTimes(1L,1L)).thenReturn(Optional.of(2));
        Mockito.when(paperService.getPaperInfo(1L,1L)).thenReturn(paper);
        Mockito.when(userService.getUserInfo(1L)).thenReturn(user);
        Mockito.when(courseService.getCourseInfo(1L)).thenReturn(course);
        Mockito.when(questionRepository.findById(1L)).thenReturn(Optional.of(question));

        PaperAnswer paperAnswer1 = new PaperAnswer();
        PaperAnswerPrimaryKey paperAnswerPrimaryKey = new PaperAnswerPrimaryKey(user,paper,2);
        paperAnswer1.setPaperAnswerPrimaryKey(paperAnswerPrimaryKey);
        paperAnswer1.setState(PaperAnswerState.NOT_FINISH);
        Answer answer = new Answer();
        AnswerPrimaryKey answerPrimaryKey = new AnswerPrimaryKey(paperAnswer1,question);
        answer.setAnswerPrimaryKey(answerPrimaryKey);
        answer.setAnswer("A");
        paperAnswer1.setAnswers(List.of(answer));

        Mockito.when(paperAnswerRepository.getOne(paperAnswerPrimaryKey)).thenReturn(paperAnswer1);
        Mockito.when(paperAnswerRepository.save(any(PaperAnswer.class))).thenAnswer(i -> i.getArguments()[0]);
        Mockito.when(answerRepository.save(any(Answer.class))).thenAnswer(i->i.getArguments()[0]);
        Mockito.when(paperRepository.getOne(paper.getId())).thenReturn(paper);

        PaperAnswer paperAnswer2 = paperAnswerService.submitAnswer(1L,1L,1L,submitAnswerForm);
        assertThat(paperAnswer2.getPaperAnswerPrimaryKey().getTimes()).isEqualTo(2);
        assertThat(paperAnswer2.getAnswers().size()).isEqualTo(1);
    }

    @Test
    public void tempSaveTest()throws Exception{
        // mockito in get paper answer
        Mockito.when(paperAnswerRepository.getMaxTimes(1L,1L)).thenReturn(Optional.of(2));
        Mockito.when(paperService.getPaperInfo(1L,1L)).thenReturn(paper);
        Mockito.when(userService.getUserInfo(1L)).thenReturn(user);
        Mockito.when(courseService.getCourseInfo(1L)).thenReturn(course);
        Mockito.when(questionRepository.findById(1L)).thenReturn(Optional.of(question));

        PaperAnswer paperAnswer1 = new PaperAnswer();
        PaperAnswerPrimaryKey paperAnswerPrimaryKey = new PaperAnswerPrimaryKey(user,paper,2);
        paperAnswer1.setPaperAnswerPrimaryKey(paperAnswerPrimaryKey);
        paperAnswer1.setState(PaperAnswerState.TEMP_SAVE);
        Mockito.when(paperRepository.getOne(1L)).thenReturn(paper);
        Answer answer = new Answer();
        AnswerPrimaryKey answerPrimaryKey = new AnswerPrimaryKey(paperAnswer1,question);
        answer.setAnswerPrimaryKey(answerPrimaryKey);
        answer.setAnswer("A");
        paperAnswer1.setAnswers(new ArrayList<>(List.of(answer)));

        Mockito.when(paperAnswerRepository.getOne(paperAnswerPrimaryKey)).thenReturn(paperAnswer1);
        Mockito.when(paperAnswerRepository.save(any(PaperAnswer.class))).thenAnswer(i -> i.getArguments()[0]);
        Mockito.when(answerRepository.save(any(Answer.class))).thenAnswer(i->i.getArguments()[0]);

        PaperAnswer paperAnswer2 = paperAnswerService.submitAnswer(1L,1L,1L,submitAnswerForm);
        assertThat(paperAnswer2.getPaperAnswerPrimaryKey().getTimes()).isEqualTo(2);
        assertThat(paperAnswer2.getAnswers().size()).isEqualTo(2);
    }

    @Test(expected = NotMatchException.class)
    public void questionNotMatchTest()throws Exception{
        // mockito in get paper answer
        Mockito.when(paperAnswerRepository.getMaxTimes(1L,1L)).thenReturn(Optional.of(2));
        Mockito.when(paperService.getPaperInfo(1L,1L)).thenReturn(paper);
        Mockito.when(userService.getUserInfo(1L)).thenReturn(user);
        Mockito.when(courseService.getCourseInfo(1L)).thenReturn(course);
        PaperAnswer paperAnswer1 = new PaperAnswer();
        PaperAnswerPrimaryKey paperAnswerPrimaryKey = new PaperAnswerPrimaryKey(user,paper,2);
        paperAnswer1.setPaperAnswerPrimaryKey(paperAnswerPrimaryKey);
        paperAnswer1.setState(PaperAnswerState.FINISHED);
        Mockito.when(paperAnswerRepository.getOne(new PaperAnswerPrimaryKey(user,paper,2))).thenReturn(paperAnswer1);
        Mockito.when(paperAnswerRepository.save(any(PaperAnswer.class))).thenAnswer(i -> i.getArguments()[0]);

        Mockito.when(paperRepository.getOne(1L)).thenReturn(paper);

        Question question2 = new Question();
        question2.setId(2L);
        Mockito.when(questionRepository.findById(2L)).thenReturn(Optional.of(question2));

        QuestionAnswer questionAnswer = new QuestionAnswer(2L,"A");
        SubmitAnswerForm submitAnswerForm = new SubmitAnswerForm(List.of(questionAnswer),"FINISHED");
        paperAnswerService.submitAnswer(1L,1L,1L,submitAnswerForm);

    }

    @Test
    public void autoMarkTest(){
        //initialize paper and questions
        Paper paper2 = new Paper();
        paper2.setId(2L);

        Question obj1 = new Question();
        obj1.setId(2L);
        obj1.setAnswer("A");
        obj1.setQuestionType(QuestionType.SINGLE_ANSWER);

        Question obj2 = new Question();
        obj2.setId(3L);
        obj2.setAnswer("AB");
        obj2.setQuestionType(QuestionType.MULTIPLE_ANSWER);

        Question sub1 = new Question();
        sub1.setId(4L);
        sub1.setQuestionType(QuestionType.SUBJECTIVE);

        List<PaperWithQuestions> paperWithQuestionsList = new ArrayList<>();
        paperWithQuestionsList.add(new PaperWithQuestions(paper2,obj1,1,5.0D));
        paperWithQuestionsList.add(new PaperWithQuestions(paper2,obj2,2,5.0D));
        paperWithQuestionsList.add(new PaperWithQuestions(paper2,sub1,3,10.0D));
        paper2.setQuestions(paperWithQuestionsList);

        //initialize paper answer
        PaperAnswer paperAnswer1 = new PaperAnswer();
        paperAnswer1.setState(PaperAnswerState.FINISHED);

        AnswerPrimaryKey answer1 = new AnswerPrimaryKey(paperAnswer1,obj1);
        AnswerPrimaryKey answer2 = new AnswerPrimaryKey(paperAnswer1,obj2);
        Answer wrong1 = new Answer(answer1,"B",0D);
        Answer right2 = new Answer(answer2,"AB",0D);

        paperAnswer1.setAnswers(List.of(wrong1,right2));

        PaperAnswer paperAnswer2 = new PaperAnswer();
        paperAnswer1.setState(PaperAnswerState.FINISHED);

        AnswerPrimaryKey answer3 = new AnswerPrimaryKey(paperAnswer2,obj1);
        AnswerPrimaryKey answer4 = new AnswerPrimaryKey(paperAnswer2,obj2);
        Answer right3 = new Answer(answer3,"A",0D);
        Answer right4 = new Answer(answer4,"AB",0D);

        paperAnswer2.setAnswers(List.of(right3,right4));

        Paper paper3 = new Paper();
        paper3.setId(3L);
        List<PaperWithQuestions> paperWithQuestionsList2 = new ArrayList<>();
        paperWithQuestionsList2.add(new PaperWithQuestions(paper3,obj1,1,5.0D));
        paperWithQuestionsList2.add(new PaperWithQuestions(paper3,obj2,2,5.0D));
        paper3.setQuestions(paperWithQuestionsList2);

        PaperAnswer paperAnswer3 = new PaperAnswer();
        paperAnswer3.setState(PaperAnswerState.FINISHED);

        AnswerPrimaryKey answer5 = new AnswerPrimaryKey(paperAnswer3,obj1);
        AnswerPrimaryKey answer6 = new AnswerPrimaryKey(paperAnswer3,obj2);
        Answer right5 = new Answer(answer5,"A",0D);
        Answer wrong6 = new Answer(answer6,"BC",0D);

        paperAnswer3.setAnswers(List.of(right5,wrong6));

        //Mockito paper
        Mockito.when(paperRepository.getOne(2L)).thenReturn(paper2);
        Mockito.when(paperRepository.getOne(3L)).thenReturn(paper3);

        //Mockito paper answer
        Mockito.when(paperAnswerRepository.getPaperAnswers(2L)).thenReturn(List.of(paperAnswer1,paperAnswer2));
        Mockito.when(paperAnswerRepository.getPaperAnswers(3L)).thenReturn(List.of(paperAnswer3));

        //invoke function
        paperAnswerService.autoMark(2L);
        paperAnswerService.autoMark(3L);

        // grade assert
        assertThat(wrong1.getGrade()).isEqualTo(0D);
        assertThat(right2.getGrade()).isEqualTo(5.0D);
        assertThat(right3.getGrade()).isEqualTo(5.0D);
        assertThat(right4.getGrade()).isEqualTo(5.0D);
        assertThat(right5.getGrade()).isEqualTo(5.0D);
        assertThat(wrong6.getGrade()).isEqualTo(0D);

        assertThat(paperAnswer1.getGrade()).isEqualTo(5.0D);
        assertThat(paperAnswer2.getGrade()).isEqualTo(10.0D);
        assertThat(paperAnswer3.getGrade()).isEqualTo(5.0D);

        //state assert
        assertThat(paperAnswer1.getState()).isEqualTo(PaperAnswerState.NOT_MARKED);
        assertThat(paperAnswer3.getState()).isEqualTo(PaperAnswerState.MARKED);
    }

    @Test
    public void getPersonalAnswerTest(){
        PaperAnswer paperAnswer1 = new PaperAnswer();
        PaperAnswerPrimaryKey paperAnswerPrimaryKey1 = new PaperAnswerPrimaryKey(user,paper,1);
        paperAnswer1.setPaperAnswerPrimaryKey(paperAnswerPrimaryKey1);
        PaperAnswer paperAnswer2 = new PaperAnswer();
        PaperAnswerPrimaryKey paperAnswerPrimaryKey2 = new PaperAnswerPrimaryKey(user,paper,2);
        paperAnswer2.setPaperAnswerPrimaryKey(paperAnswerPrimaryKey2);

        Mockito.when(paperAnswerRepository.getPersonalPaperAnswer(1L,1L)).
                thenReturn(List.of(paperAnswer1,paperAnswer2));

        List<PaperAnswer> paperAnswerList = paperAnswerService.getPersonalPaperAnswer(1L,1L);
        assertThat(paperAnswerList).contains(paperAnswer1);
        assertThat(paperAnswerList).contains(paperAnswer2);
    }

    @Test(expected = NotFoundException.class)
    public void answerNotFound(){
       Mockito.when(userService.getUserInfo(1L)).thenReturn(user);
       Mockito.when(paperRepository.findById(1L)).thenReturn(Optional.of(paper));
       PaperAnswerPrimaryKey paperAnswerPrimaryKey = new PaperAnswerPrimaryKey(user,paper,1);
       Mockito.when(paperAnswerRepository.findById(paperAnswerPrimaryKey)).thenReturn(Optional.empty());

       paperAnswerService.markStudentPaper(1L,1L,1L,1,null);
    }

    @Test(expected = NotFoundException.class)
    public void questionNotFound(){
        Mockito.when(userService.getUserInfo(1L)).thenReturn(user);
        Mockito.when(paperRepository.findById(1L)).thenReturn(Optional.of(paper));
        PaperAnswerPrimaryKey paperAnswerPrimaryKey = new PaperAnswerPrimaryKey(user,paper,1);
        PaperAnswer paperAnswer = new PaperAnswer();
        Mockito.when(paperAnswerRepository.findById(paperAnswerPrimaryKey)).thenReturn(Optional.of(paperAnswer));
        Mockito.when(questionRepository.findById(3L)).thenReturn(Optional.empty());
        MarkForm markForm = new MarkForm();
        markForm.setQuestionId(3L);

        paperAnswerService.markStudentPaper(1L,1L,1L,1 ,Set.of(markForm));
    }

//    @Test(expected = NotFoundException.class)
//    public void answerNotFoundInPaper(){
//        Mockito.when(userService.getUserInfo(1L)).thenReturn(user);
//        Mockito.when(paperRepository.findById(1L)).thenReturn(Optional.of(paper));
//        PaperAnswerPrimaryKey paperAnswerPrimaryKey = new PaperAnswerPrimaryKey(user,paper,1);
//        PaperAnswer paperAnswer = new PaperAnswer();
//        Mockito.when(paperAnswerRepository.findById(paperAnswerPrimaryKey)).thenReturn(Optional.of(paperAnswer));
//        Mockito.when(questionRepository.findById(3L)).thenReturn(O)
//        MarkForm markForm = new MarkForm();
//        markForm.setQuestionId(3L);
//
//        paperAnswerService.markStudentPaper(1L,1L,1, Set.of(markForm));
//    }




}
