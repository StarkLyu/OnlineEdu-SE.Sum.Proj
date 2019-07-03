package com.se231.onlineedu.model;


import javax.persistence.*;
import java.util.List;


/**
 * Question class
 *
 * Entity class for question
 *
 * @author Yuxuan Liu
 *
 * @date 2019/09/03
 */
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String question;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    private CoursePrototype coursePrototype;

    @ElementCollection
    @CollectionTable(name="QuestionImage", joinColumns = @JoinColumn(name="question_id"))
    private List<String> images;

    public Question() {
    }

    public Question(String question, QuestionType questionType, String answer, CoursePrototype coursePrototype, List<String> images) {
        this.question = question;
        this.questionType = questionType;
        this.answer = answer;
        this.coursePrototype = coursePrototype;
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public CoursePrototype getCoursePrototype() {
        return coursePrototype;
    }

    public void setCoursePrototype(CoursePrototype coursePrototype) {
        this.coursePrototype = coursePrototype;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
