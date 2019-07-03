package com.se231.onlineedu.model;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class AnswerPrimaryKey implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PaperAnswer paperAnswer;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    public AnswerPrimaryKey(PaperAnswer paperAnswer, Question question) {
        this.paperAnswer = paperAnswer;
        this.question = question;
    }

    public PaperAnswer getPaperAnswer() {
        return paperAnswer;
    }

    public void setPaperAnswer(PaperAnswer paperAnswer) {
        this.paperAnswer = paperAnswer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public AnswerPrimaryKey() {
    }
}
