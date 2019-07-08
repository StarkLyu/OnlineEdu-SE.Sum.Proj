package com.se231.onlineedu.serviceimpl;

import com.se231.onlineedu.model.CoursePrototype;
import com.se231.onlineedu.model.Question;
import com.se231.onlineedu.model.QuestionType;
import com.se231.onlineedu.repository.CoursePrototypeRepository;
import com.se231.onlineedu.repository.QuestionRepository;
import com.se231.onlineedu.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Question Service Interface Implementation Class
 *
 * contains main service logic related to question.
 *
 * @author Zhe Li
 *
 * @date 2019/7/5
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    private QuestionRepository questionRepository;

    private CoursePrototypeRepository coursePrototypeRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, CoursePrototypeRepository coursePrototypeRepository) {
        this.questionRepository = questionRepository;
        this.coursePrototypeRepository = coursePrototypeRepository;
    }

    @Override
    public Question submitQuestion(Long coursePrototypeId, QuestionType questionType,
                            String questionText, String answer)throws Exception{
        CoursePrototype coursePrototype = coursePrototypeRepository.findById(coursePrototypeId).orElseThrow(()->new Exception("No corresponding course"));
        Question question=new Question();
        question.setQuestion(questionText);
        question.setAnswer(answer);
        question.setCoursePrototype(coursePrototype);
        question.setQuestionType(questionType);
        return questionRepository.save(question);
    }
}
