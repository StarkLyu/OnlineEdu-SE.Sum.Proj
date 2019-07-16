package com.se231.onlineedu.message.request;

import javax.persistence.OneToMany;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import com.se231.onlineedu.model.TimeSlot;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Create Course form class
 *  *
 *  * A form for teaching admin to create a new course.
 *  *
 *  * @author Zhe Li
 *  *
 *  * @date 2019/7/4
 */
@ApiModel("申请创建课程的表单")
public class CourseApplicationForm {
    @NotNull
    @FutureOrPresent
    private Date startDate;

    @NotNull
    @Future
    private Date endDate;

    @NotNull
    @ApiModelProperty("课程名称")
    private String courseTitle;

    @ApiModelProperty("地点")
    private String location;

    @OneToMany
    @ApiModelProperty("上课时间段")
    private List<TimeSlotForm> timeSlots;

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

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<TimeSlotForm> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlotForm> timeSlots) {
        this.timeSlots = timeSlots;
    }
}
