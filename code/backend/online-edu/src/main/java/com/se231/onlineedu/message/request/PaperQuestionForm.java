package com.se231.onlineedu.message.request;

import javax.validation.constraints.NotNull;

/**
 * Paper's Question Form Class
 *
 * this class is used to generate a entire paper form
 *
 * @author Zhe Li
 *
 * @date 2019/7/5
 */
public class PaperQuestionForm {

    @NotNull
    private int questionNumber;

    @NotNull
    private Long questionId;

    private int score;

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int socre) {
        this.score = socre;
    }
}
