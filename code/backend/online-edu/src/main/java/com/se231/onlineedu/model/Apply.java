package com.se231.onlineedu.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 * Teacher Apply for Course Class
 *
 * This weak entity is used to record the application for a course prototype of a teacher.
 *
 * @author Zhe Li
 *
 * @date 2019/7/3
 */

@Entity
@Table(name = "Teacher_Apply_For_Course")
public class Apply {

    @EmbeddedId
    private ApplyPrimaryKey applicationForCoursePK;

    /**
     * state is used to represent whether the application is examined or not
     * 0 represent not examined yet
     * 1 represent approval
     * -1 represent disapproval
     */
    @NotNull
    private int state;

    public Apply() {
    }

    public Apply(ApplyPrimaryKey applicationForCoursePK) {
        this.applicationForCoursePK = applicationForCoursePK;
        this.state=0;
    }

    public ApplyPrimaryKey getApplicationForCoursePK() {
        return applicationForCoursePK;
    }

    public void setApplicationForCoursePK(ApplyPrimaryKey applicationForCoursePK) {
        this.applicationForCoursePK = applicationForCoursePK;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
