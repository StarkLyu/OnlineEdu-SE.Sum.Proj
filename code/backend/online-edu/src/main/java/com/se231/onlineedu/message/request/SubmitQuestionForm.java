package com.se231.onlineedu.message.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *  Submit Question form bean
 *
 *  this bean is used to help teaching admin upload a new question.
 *
 * @author Zhe Li
 *
 * @date 2019/7/5
 */
public class SubmitQuestionForm {

    @NotBlank
    private String questionType;

    @Size(max = 1000)
    private String question;

    @Size(max = 1000)
    private String answer;

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
