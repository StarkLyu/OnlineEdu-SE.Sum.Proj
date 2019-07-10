package com.se231.onlineedu.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    @Override
    public PaperAnswer submitAnswer(Long userId, Long courseId, Long paperId, Map<Long, String> answers) throws Exception {
        double totalScore=0;
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User Not Found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(()->new RuntimeException("Course Not Found"));
        Paper paper = paperRepository.findById(paperId)
                .orElseThrow(()->new RuntimeException("Paper Not Found"));
        PaperAnswerPrimaryKey paperAnswerPrimaryKey= new PaperAnswerPrimaryKey(user,paper,0);
        PaperAnswer paperAnswer = new PaperAnswer(paperAnswerPrimaryKey);
        paperAnswer = paperAnswerRepository.save(paperAnswer);
        List<Answer> answerList= new ArrayList<>();
        for (Map.Entry<Long,String> entry : answers.entrySet()){
            Question question = questionRepository.findById(entry.getKey())
                    .orElseThrow(() -> new RuntimeException("Question Not Found"));
            PaperWithQuestions paperWithQuestions =
                    paperWithQuestionsRepository.findById(new PaperWithQuestionsPrimaryKey(paper,question))
                    .orElseThrow(()->new RuntimeException("Question Not Found"));
            AnswerPrimaryKey answerPrimaryKey = new AnswerPrimaryKey(paperAnswer,question);
            double score= entry.getValue().equals(question.getAnswer())?paperWithQuestions.getScore():0;
            Answer answer = new Answer(answerPrimaryKey,entry.getValue(),score);
            answerList.add(answerRepository.save(answer));
            totalScore+=score;
        }
        paperAnswer.setGrade(totalScore);
        paperAnswer.setAnswers(answerList);
        return paperAnswerRepository.save(paperAnswer);
    }
}
