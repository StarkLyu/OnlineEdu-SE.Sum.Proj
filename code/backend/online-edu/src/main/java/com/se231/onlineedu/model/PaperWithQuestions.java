package com.se231.onlineedu.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Paper with questions entity class
 *
 * the weak entity used to record the questions in paper
 *
 * @author Zhe Li
 *
 * @date 2019/7/5
 */
@Entity
@Table(name = "paper_with_questions")
public class PaperWithQuestions {

    @EmbeddedId
    private PaperWithQuestionsPrimaryKey paperWithQuestionsPrimaryKey;

    private int questionNumber;

    private int score;

    public PaperWithQuestions(Paper paper, Question question, int questionNumber, int score) {
        this.paperWithQuestionsPrimaryKey = new PaperWithQuestionsPrimaryKey(paper,question);
        this.questionNumber = questionNumber;
        this.score = score;
    }

    public PaperWithQuestions() {
    }

    public PaperWithQuestionsPrimaryKey getPaperWithQuestionsPrimaryKey() {
        return paperWithQuestionsPrimaryKey;
    }

    public void setPaperAnswerPrimaryKey(PaperWithQuestionsPrimaryKey paperWithQuestionsPrimaryKey) {
        this.paperWithQuestionsPrimaryKey = paperWithQuestionsPrimaryKey;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
