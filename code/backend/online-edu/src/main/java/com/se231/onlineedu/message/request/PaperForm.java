package com.se231.onlineedu.message.request;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Paper Form Class
 *
 * this class is used to help teacher of a specific course add a paper into the course.
 *
 * @author Zhe Li
 *
 * @date 2019/7/5
 */
public class PaperForm {
    @NotEmpty
    private List<PaperQuestionForm> questionFormList;

    @NotNull
    @FutureOrPresent
    private Date start;

    @NotNull
    @Future
    private Date end;

    public PaperForm() {
    }

    public List<PaperQuestionForm> getQuestionFormList() {
        return questionFormList;
    }

    public void setQuestionFormList(List<PaperQuestionForm> questionFormList) {
        this.questionFormList = questionFormList;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
