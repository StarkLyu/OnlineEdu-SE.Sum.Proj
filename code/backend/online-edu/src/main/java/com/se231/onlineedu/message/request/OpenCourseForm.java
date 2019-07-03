package com.se231.onlineedu.message.request;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;

/**
 * Open Course Form Class
 *
 * mapping to the form of open course
 *
 * @author zhe li
 * @date  2019/7/1
 */
public class OpenCourseForm {
    @NotBlank
    @Size(min = 3,max = 30)
    private String courseName;

    @NotBlank
    @Future
    private Date startDate;

    @NotBlank
    @Future
    private Date endDate;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
