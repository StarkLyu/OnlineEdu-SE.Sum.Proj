package com.se231.onlineedu.serviceimpl;

import java.util.Date;
import java.util.List;
import com.se231.onlineedu.message.request.PaperQuestionForm;
import com.se231.onlineedu.model.Paper;
import com.se231.onlineedu.model.PaperWithQuestions;
import com.se231.onlineedu.model.Question;
import com.se231.onlineedu.repository.PaperRepository;
import com.se231.onlineedu.repository.PaperWithQuestionsRepository;
import com.se231.onlineedu.repository.QuestionRepository;
import com.se231.onlineedu.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Paper Service Interface Implementation Class
 *
 * contains main service logic related to paper.
 *
 * @author Zhe Li
 *
 * @date 2019/7/5
 */
@Service
public class PaperServiceImpl implements PaperService {

    private PaperWithQuestionsRepository paperWithQuestionsRepository;

    private QuestionRepository questionRepository;

    private PaperRepository paperRepository;

    @Autowired
    public PaperServiceImpl(PaperWithQuestionsRepository paperWithQuestionsRepository, QuestionRepository questionRepository, PaperRepository paperRepository) {
        this.paperWithQuestionsRepository = paperWithQuestionsRepository;
        this.questionRepository = questionRepository;
        this.paperRepository = paperRepository;
    }

    @Override
    public Paper addNewPaper(List<PaperQuestionForm> paperQuestionFormsList, Long courseId,
                             Date start, Date end)throws Exception{
        Paper paper=new Paper();
        paper = paperRepository.save(paper);
        for(PaperQuestionForm paperQuestionForm:paperQuestionFormsList){
                Question question = questionRepository.findById(paperQuestionForm.getQuestionId())
                        .orElseThrow(()->new Exception("No corresponding question"));
                PaperWithQuestions paperWithQuestions = new PaperWithQuestions(paper,question,
                        paperQuestionForm.getQuestionNumber(),paperQuestionForm.getScore());
                System.out.println(paperWithQuestions.getPaperWithQuestionsPrimaryKey().getQuestion().getQuestion());
                paperWithQuestionsRepository.save(paperWithQuestions);
        }
        paper.setStart(start);
        paper.setEnd(end);
        return paperRepository.save(paper);
    }

}
