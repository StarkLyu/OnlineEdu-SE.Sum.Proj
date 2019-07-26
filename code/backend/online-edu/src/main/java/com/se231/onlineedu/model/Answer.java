package com.se231.onlineedu.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Answer Entity Class
 *
 * answer for a specific question in the answer of a paper.
 *
 * @author Yuxuan Liu
 *
 * @date 2019/7/4
 */
@ApiModel("学生对一个特定试卷上特定题目的回答")
@Entity
public class Answer {
    @ApiModelProperty("联合主键类,组成为paper answer和question")
    @EmbeddedId
    private AnswerPrimaryKey answerPrimaryKey;

    @ApiModelProperty("学生的作答")
    private String answer;

    @ApiModelProperty("该题的得分")
    private double grade;

    @ApiModelProperty("该题的评语")
    private String comment;

    public Answer() {
    }

    public Answer(AnswerPrimaryKey answerPrimaryKey, String answer, double grade) {
        this.answerPrimaryKey = answerPrimaryKey;
        this.answer = answer;
        this.grade = grade;
    }

    public AnswerPrimaryKey getAnswerPrimaryKey() {
        return answerPrimaryKey;
    }

    public void setAnswerPrimaryKey(AnswerPrimaryKey answerPrimaryKey) {
        this.answerPrimaryKey = answerPrimaryKey;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
