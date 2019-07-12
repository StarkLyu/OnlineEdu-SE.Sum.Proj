package com.se231.onlineedu.message.request;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Paper Form Class
 *
 * this class is used to help teacher of a specific course add a paper into the course.
 *
 * @author Zhe Li
 *
 * @date 2019/7/5
 */
@ApiModel("增加新试卷的表单")
public class PaperForm {

    @ApiModelProperty("题目列表")
    @NotEmpty
    private List<PaperQuestionForm> questionFormList;

    @ApiModelProperty(value = "开始时间",example = "2019-07-12")
    @NotNull
    @FutureOrPresent
    private Date start;

    @ApiModelProperty("结束时间")
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
