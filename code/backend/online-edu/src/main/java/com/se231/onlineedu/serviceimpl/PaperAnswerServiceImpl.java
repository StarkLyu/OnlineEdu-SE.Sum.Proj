package com.se231.onlineedu.serviceimpl;

import java.io.IOException;
import java.util.*;

import com.se231.onlineedu.exception.NotFoundException;
import com.se231.onlineedu.exception.ValidationException;
import com.se231.onlineedu.message.request.MarkForm;
import com.se231.onlineedu.message.request.QuestionAnswer;
import com.se231.onlineedu.message.request.SubmitAnswerForm;
import com.se231.onlineedu.model.*;
import com.se231.onlineedu.repository.*;
import com.se231.onlineedu.service.CourseService;
import com.se231.onlineedu.service.PaperAnswerService;
import com.se231.onlineedu.service.UserService;
import com.se231.onlineedu.util.FileCheckUtil;
import com.se231.onlineedu.util.SaveFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    private static final int MAX_TIMES = 3;

    private static final int LIMIT = 5120000;

    @Override
    public PaperAnswer submitAnswer(Long userId, Long courseId, Long paperId, SubmitAnswerForm form) {
        PaperAnswer paperAnswer = getPaperAnswer(userId, courseId, paperId);
        for (QuestionAnswer questionAnswer :form.getAnswerList()){
            Question question = questionRepository.findById(questionAnswer.getQuestionId())
                    .orElseThrow(() -> new RuntimeException("Question Not Found"));
            AnswerPrimaryKey answerPrimaryKey = new AnswerPrimaryKey(paperAnswer,question);
            Answer answer = new Answer(answerPrimaryKey,questionAnswer.getAnswer(),0);
            paperAnswer.getAnswers().add(answerRepository.save(answer));
        }
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

    @Override
    public PaperAnswer markStudentPaper(Long studentId, Long paperId, Integer times, Set<MarkForm> markForms) {
        User student = userService.getUserInfo(studentId);
        Paper paper = paperRepository.findById(paperId).orElseThrow(()->new NotFoundException("No corresponding paper"));
        PaperAnswerPrimaryKey paperAnswerPrimaryKey= new PaperAnswerPrimaryKey(student,paper,times);
        PaperAnswer answer = paperAnswerRepository.findById(paperAnswerPrimaryKey)
                .orElseThrow(()->new NotFoundException("No corresponding paper answer"));
        Map<Long,String> commentMap = new HashMap<>(markForms.size());
        Map<Long,Double> scoreMap = new HashMap<>(markForms.size());
        markForms.forEach(mark->{
            commentMap.put(mark.getQuestionId(),mark.getComment());
            scoreMap.put(mark.getQuestionId(),mark.getScore());
        });
        answer.getAnswers().forEach(answer1 -> {
            answer1.setGrade(scoreMap.get(answer1.getAnswerPrimaryKey().getQuestion().getId()));
            answer1.setComment(commentMap.get(answer1.getAnswerPrimaryKey().getQuestion().getId()));
        });
        return paperAnswerRepository.save(answer);
    }

    @Override
    public PaperAnswer submitSubjectiveQuestion(Long courseId, Long userId, Long paperId, Long questionId,
                                                String answerText, MultipartFile[] images,MultipartFile file,
                                                PaperAnswerState state) {
        //File check
        /**
        empty space to fill
         */
        PaperAnswer paperAnswer = getPaperAnswer(userId, courseId, paperId);
        paperAnswer.setState(state);
        Question question = questionRepository.findById(questionId)
                .orElseThrow(()->new NotFoundException("No corresponding question"));
        AnswerPrimaryKey answerPrimaryKey = new AnswerPrimaryKey(paperAnswer,question);
        Answer answer = new Answer(answerPrimaryKey,answerText,0);
        try {
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            answer.setResource(SaveFileUtil.saveFile(file,suffix));
            answer.setImageUrls(SaveFileUtil.saveImages(images, LIMIT));

        } catch (IOException e){
            e.printStackTrace();
        }
        paperAnswer.getAnswers().add(answer);
        return paperAnswerRepository.save(paperAnswer);
    }

    private PaperAnswer getPaperAnswer(Long userId,Long courseId,Long paperId){
        //initialize( found corresponding user and paper)
        User user = userService.getUserInfo(userId);
        Course course = courseService.getCourseInfo(courseId);
        Paper paper = paperRepository.findById(paperId).orElseThrow(()->new NotFoundException("Paper Not Found"));
        if(!paper.getCourse().equals(course)){
            throw new ValidationException("This Course Doesn't Have This Paper.");
        }

        //get times the user has answered
        int times=paperAnswerRepository.getMaxTimes(userId,paperId).orElse(0);
        if(times>0) {
            PaperAnswerPrimaryKey lastAnswerPrimaryKey = new PaperAnswerPrimaryKey(user, paper, times);
            PaperAnswer lastAnswer = paperAnswerRepository.getOne(lastAnswerPrimaryKey);
            if (lastAnswer.getState().equals(PaperAnswerState.NOT_FINISH)){
                paperAnswerRepository.delete(lastAnswer);
                times--;
            }
            if(lastAnswer.getState().equals(PaperAnswerState.TEMP_SAVE)){
                return lastAnswer;
            }
        }if(times==MAX_TIMES){
            throw new RuntimeException("You Have Answered Three Times");
        }
        PaperAnswerPrimaryKey paperAnswerPrimaryKey= new PaperAnswerPrimaryKey(user,paper,times+1);
        PaperAnswer paperAnswer = new PaperAnswer(paperAnswerPrimaryKey);
        return paperAnswerRepository.save(paperAnswer);
    }
}
