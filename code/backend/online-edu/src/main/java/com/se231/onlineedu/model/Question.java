package com.se231.onlineedu.model;


import javax.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * Question class
 *
 * Entity class for question
 *
 * @author Yuxuan Liu
 *
 * @date 2019/09/03
 */
@ApiModel("题目的实体类")
@Entity
public class Question {
    @ApiModelProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("问题的描述,含选项等")
    @Lob
    private String question;

    @ApiModelProperty("题目的类型,有如下类型:T_OR_F,SINGLE_ANSWER,MULTIPLE_ANSWER,SUBJECTIVE")
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @ApiModelProperty("标准答案")
    private String answer;

    @ApiModelProperty(value = "该题目所属的课程原型")
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private CoursePrototype coursePrototype;

    @ApiModelProperty("该题目的图片集")
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
