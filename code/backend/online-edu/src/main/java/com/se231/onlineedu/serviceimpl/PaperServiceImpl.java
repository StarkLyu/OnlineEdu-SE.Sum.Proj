package com.se231.onlineedu.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import com.se231.onlineedu.message.request.PaperForm;
import com.se231.onlineedu.message.request.PaperQuestionForm;
import com.se231.onlineedu.model.*;
import com.se231.onlineedu.repository.*;
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

    private SectionRepository sectionRepository;

    private CourseRepository courseRepository;

    @Autowired
    public PaperServiceImpl(PaperWithQuestionsRepository paperWithQuestionsRepository, QuestionRepository questionRepository,
                            PaperRepository paperRepository,SectionRepository sectionRepository,CourseRepository courseRepository) {
        this.paperWithQuestionsRepository = paperWithQuestionsRepository;
        this.questionRepository = questionRepository;
        this.paperRepository = paperRepository;
        this.sectionRepository=sectionRepository;
        this.courseRepository=courseRepository;
    }

    @Override
    public Paper addNewPaper(PaperForm form, Long courseId)throws Exception{
        Paper paper=new Paper();
        paper = paperRepository.save(paper);
        List <PaperWithQuestions> questions = new ArrayList<>();
        Course course = courseRepository.findById(courseId)
                .orElseThrow(()->new Exception("No corresponding course"));
        for(PaperQuestionForm paperQuestionForm:form.getQuestionFormList()){
                Question question = questionRepository.findById(paperQuestionForm.getQuestionId())
                        .orElseThrow(()->new Exception("No corresponding question"));
                PaperWithQuestions paperWithQuestions = new PaperWithQuestions(paper,question,
                        paperQuestionForm.getQuestionNumber(),paperQuestionForm.getScore());
                questions.add(paperWithQuestionsRepository.save(paperWithQuestions));
        }
        paper.setQuestions(questions);
        paper.setStart(form.getStart());
        paper.setEnd(form.getEnd());
        paper.setTitle(form.getTitle());
        paper.setDescription(form.getDescription());
        paper.setCourse(course);
        return paperRepository.save(paper);
    }

    @Override
    public Section issuePaper(Long courseId, int secNo, Long paperId) throws Exception {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(()->new Exception("No corresponding course"));
        Section section = sectionRepository.findById(new SectionPrimaryKey(course,secNo))
                .orElseThrow(()->new Exception("No corresponding question"));
        Paper paper = paperRepository.findById(paperId)
                .orElseThrow(()->new Exception("No corresponding paper"));
        section.getPapers().add(paper);
        return sectionRepository.save(section);
    }
}
