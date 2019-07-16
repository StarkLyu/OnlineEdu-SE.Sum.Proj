package com.se231.onlineedu.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import com.se231.onlineedu.message.request.QuestionAnswer;
import com.se231.onlineedu.message.request.SubmitAnswerForm;
import com.se231.onlineedu.model.*;
import com.se231.onlineedu.repository.*;
import com.se231.onlineedu.service.PaperAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zhe Li
 * @date 2019/07/10
 */
@Service
public class PaperAnswerServiceImpl implements PaperAnswerService {

    @Autowired UserRepository userRepository;

    @Autowired CourseRepository courseRepository;

    @Autowired PaperRepository paperRepository;

    @Autowired PaperWithQuestionsRepository paperWithQuestionsRepository;

    @Autowired QuestionRepository questionRepository;

    @Autowired AnswerRepository answerRepository;

    @Autowired PaperAnswerRepository paperAnswerRepository;

    private static final int MAX_TIMES=3;

    @Override
    public PaperAnswer submitAnswer(Long userId, Long courseId, Long paperId, SubmitAnswerForm form) throws Exception {
        //initialize( found corresponding user and paper)
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User Not Found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(()->new RuntimeException("Course Not Found"));
        Paper paper = paperRepository.findById(paperId)
                .orElseThrow(()->new RuntimeException("Paper Not Found"));
        if(!paper.getCourse().equals(course)){
            throw new RuntimeException("This Course Doesn't Have This Paper.");
        }

        //get times the user has answered
        int times=paperAnswerRepository.getMaxTimes(userId,paperId).orElse(0);
        if(times==MAX_TIMES){
            throw new RuntimeException("You Have Answered Three Times");
        }
        PaperAnswerPrimaryKey paperAnswerPrimaryKey= new PaperAnswerPrimaryKey(user,paper,times+1);

        //if the paper hasn't finished , just simply save the paper and return
        if(PaperAnswerState.valueOf(form.getState())==PaperAnswerState.NOT_FINISH){

        }
        double totalScore=0;

        PaperAnswer paperAnswer = new PaperAnswer(paperAnswerPrimaryKey);
        paperAnswer = paperAnswerRepository.save(paperAnswer);
        List<Answer> answerList= new ArrayList<>();
        for (QuestionAnswer questionAnswer :form.getAnswerList()){
            Question question = questionRepository.findById(questionAnswer.getQuestionId())
                    .orElseThrow(() -> new RuntimeException("Question Not Found"));
            PaperWithQuestions paperWithQuestions =
                    paperWithQuestionsRepository.findById(new PaperWithQuestionsPrimaryKey(paper,question))
                    .orElseThrow(()->new RuntimeException("Question Not Found"));
            AnswerPrimaryKey answerPrimaryKey = new AnswerPrimaryKey(paperAnswer,question);
            //若题目为主观题，默认不做批改，直接不打分留题。若为客观题则直接检测与答案是否匹配。
            double score= (!question.getQuestionType().equals(QuestionType.SUBJECTIVE)&&
                    questionAnswer.getAnswer().equals(question.getAnswer()))?paperWithQuestions.getScore():0;
            Answer answer = new Answer(answerPrimaryKey,questionAnswer.getAnswer(),score);
            answerList.add(answerRepository.save(answer));
            totalScore+=score;
        }
        paperAnswer.setGrade(totalScore);
        paperAnswer.setAnswers(answerList);
        return paperAnswerRepository.save(paperAnswer);
    }
}
