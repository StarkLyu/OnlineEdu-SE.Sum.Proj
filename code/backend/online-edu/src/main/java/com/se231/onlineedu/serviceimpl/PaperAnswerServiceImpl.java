package com.se231.onlineedu.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.se231.onlineedu.exception.NotFoundException;
import com.se231.onlineedu.exception.ValidationException;
import com.se231.onlineedu.message.request.QuestionAnswer;
import com.se231.onlineedu.message.request.SubmitAnswerForm;
import com.se231.onlineedu.model.*;
import com.se231.onlineedu.repository.*;
import com.se231.onlineedu.service.CourseService;
import com.se231.onlineedu.service.PaperAnswerService;
import com.se231.onlineedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zhe Li
 * @date 2019/07/10
 */
@Service
public class PaperAnswerServiceImpl implements PaperAnswerService {

    @Autowired
    UserService userService;

    @Autowired
    CourseService courseService;

    @Autowired
    PaperRepository paperRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    PaperAnswerRepository paperAnswerRepository;

    private static final int MAX_TIMES=3;

    @Override
    public PaperAnswer submitAnswer(Long userId, Long courseId, Long paperId, SubmitAnswerForm form) {
        //initialize( found corresponding user and paper)
        User user = userService.getUserInfo(userId);
        Course course = courseService.getCourseInfo(courseId);
        Paper paper = paperRepository.findById(paperId).orElseThrow(()->new NotFoundException("Paper Not Found"));
        if(!paper.getCourse().equals(course)){
            throw new ValidationException("This Course Doesn't Have This Paper.");
        }

        //get times the user has answered
        int times=paperAnswerRepository.getMaxTimes(userId,paperId).orElse(0);
        if(times==MAX_TIMES){
            throw new RuntimeException("You Have Answered Three Times");
        }
        PaperAnswerPrimaryKey paperAnswerPrimaryKey= new PaperAnswerPrimaryKey(user,paper,times+1);
        PaperAnswer paperAnswer = new PaperAnswer(paperAnswerPrimaryKey);
        paperAnswer = paperAnswerRepository.save(paperAnswer);
        List<Answer> answerList= new ArrayList<>();
        for (QuestionAnswer questionAnswer :form.getAnswerList()){
            Question question = questionRepository.findById(questionAnswer.getQuestionId())
                    .orElseThrow(() -> new RuntimeException("Question Not Found"));
            AnswerPrimaryKey answerPrimaryKey = new AnswerPrimaryKey(paperAnswer,question);
            Answer answer = new Answer(answerPrimaryKey,questionAnswer.getAnswer(),0);
            answerList.add(answerRepository.save(answer));
        }
        paperAnswer.setAnswers(answerList);
        paperAnswer.setState(PaperAnswerState.valueOf(form.getState()));
        return paperAnswerRepository.save(paperAnswer);
    }

    @Override
    public void autoMark(Long paperId) {
        Paper paper = paperRepository.getOne(paperId);
        Map<Long,Double> scoreTable= new HashMap<>(paper.getQuestions().size());
        boolean hasSubjective=false;
        for (PaperWithQuestions questions:paper.getQuestions()) {
            if(questions.getPaperWithQuestionsPrimaryKey().getQuestion().getQuestionType().equals(QuestionType.SUBJECTIVE)){
                hasSubjective=true;
            }
            scoreTable.put(questions.getPaperWithQuestionsPrimaryKey().getQuestion().getId(),questions.getScore());
        }
        final boolean sub = hasSubjective;
        List<PaperAnswer> paperAnswerList = paperAnswerRepository.getPaperAnswers(paperId);
        paperAnswerList.forEach(paperAnswer ->{
            double totalScore=0D;
            for(Answer answer:paperAnswer.getAnswers()){
                //若题目为主观题，默认不做批改，直接不打分留题。若为客观题则直接检测与答案是否匹配。
                Question question = answer.getAnswerPrimaryKey().getQuestion();
                double score= (!question.getQuestionType().equals(QuestionType.SUBJECTIVE)&&
                    answer.getAnswer().equals(question.getAnswer()))?scoreTable.get(question.getId()):0;
                answer.setGrade(score);
                answerRepository.save(answer);
                totalScore+=score;
            }
            PaperAnswerState state = sub ? PaperAnswerState.NOT_MARKED : PaperAnswerState.MARKED;
            paperAnswer.setGrade(totalScore);
            paperAnswer.setState(state);
            paperAnswerRepository.save(paperAnswer);
        });
    }

    @Override
    public List<PaperAnswer> getPersonalPaperAnswer(Long paperId, Long userId) {
        return paperAnswerRepository.getPersonalPaperAnswer(paperId,userId);
    }
}
