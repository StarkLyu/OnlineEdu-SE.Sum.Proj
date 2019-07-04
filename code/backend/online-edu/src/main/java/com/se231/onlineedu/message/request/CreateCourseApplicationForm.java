package com.se231.onlineedu.message.request;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Create Course form class
 *  *
 *  * A form for teaching admin to create a new course.
 *  *
 *  * @author Zhe Li
 *  *
 *  * @date 2019/7/4
 */
public class CreateCourseApplicationForm {
    @NotNull
    @FutureOrPresent
    private Date startDate;

    @NotNull
    @Future
    private Date endDate;

    @NotNull
    private Long coursePrototypeId;

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

    public Long getCoursePrototypeId() {
        return coursePrototypeId;
    }

    public void setCoursePrototypeId(Long coursePrototypeId) {
        this.coursePrototypeId = coursePrototypeId;
    }
}
