package com.se231.onlineedu.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Answer {
    @EmbeddedId
    private AnswerPrimaryKey answerPrimaryKey;

    private String answer;

    private double grade;

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
}
