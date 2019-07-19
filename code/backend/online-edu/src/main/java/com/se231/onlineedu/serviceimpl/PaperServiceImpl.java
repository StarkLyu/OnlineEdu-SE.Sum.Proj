package com.se231.onlineedu.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import com.se231.onlineedu.exception.NotFoundException;
import com.se231.onlineedu.message.request.PaperForm;
import com.se231.onlineedu.message.request.PaperQuestionForm;
import com.se231.onlineedu.model.*;
import com.se231.onlineedu.repository.*;
import com.se231.onlineedu.scheduler.SchedulerHandler;
import com.se231.onlineedu.service.CourseService;
import com.se231.onlineedu.service.PaperService;
import org.quartz.SchedulerException;
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

    @Autowired
    private PaperWithQuestionsRepository paperWithQuestionsRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private CourseService courseService;


    @Override
    public Paper addNewPaper(PaperForm form, Long courseId) {
        Paper paper=new Paper();
        paper = paperRepository.save(paper);
        List <PaperWithQuestions> questions = new ArrayList<>();
        Course course = courseService.getCourseInfo(courseId);
        for(PaperQuestionForm paperQuestionForm:form.getQuestionFormList()){
                Question question = questionRepository.findById(paperQuestionForm.getQuestionId())
                        .orElseThrow(()->new NotFoundException("No corresponding question"));
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
        paper=paperRepository.save(paper);
        try{
            SchedulerHandler.setAnswerStateAndAutoMark(form.getEnd(),paper.getId());
        }catch (SchedulerException e){
            e.printStackTrace();
        }
        return paper;
    }

    @Override
    public Section issuePaper(Long courseId, int secNo, Long paperId) {
        Course course = courseService.getCourseInfo(courseId);
        Section section = sectionRepository.findById(new SectionPrimaryKey(course,secNo))
                .orElseThrow(()->new NotFoundException("No corresponding question"));
        Paper paper = paperRepository.findById(paperId)
                .orElseThrow(()->new NotFoundException("No corresponding paper"));
        section.getPapers().add(paper);
        return sectionRepository.save(section);
    }
}
