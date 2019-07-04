package com.se231.onlineedu.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class PaperAnswer {
    @EmbeddedId
    private PaperAnswerPrimaryKey paperAnswerPrimaryKey;

    @OneToMany
    private List<Answer> answers;

    private double grade;

    public PaperAnswer(PaperAnswerPrimaryKey paperAnswerPrimaryKey, List<Answer> answers, double grade) {
        this.paperAnswerPrimaryKey = paperAnswerPrimaryKey;
        this.answers = answers;
        this.grade = grade;
    }

    public PaperAnswer(PaperAnswerPrimaryKey paperAnswerPrimaryKey) {
        this.paperAnswerPrimaryKey = paperAnswerPrimaryKey;
    }

    public PaperAnswerPrimaryKey getPaperAnswerPrimaryKey() {
        return paperAnswerPrimaryKey;
    }

    public void setPaperAnswerPrimaryKey(PaperAnswerPrimaryKey paperAnswerPrimaryKey) {
        this.paperAnswerPrimaryKey = paperAnswerPrimaryKey;
    }

    public PaperAnswer() {
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
