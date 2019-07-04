package com.se231.onlineedu.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


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
@JsonIgnoreProperties("hibernateLazyInitializer")
public class Apply {

    @EmbeddedId
    private ApplyPrimaryKey applicationForCoursePK;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ApplyState applyState;

    public Apply() {
    }

    public Apply(ApplyPrimaryKey applicationForCoursePK) {
        this.applicationForCoursePK = applicationForCoursePK;
        this.applyState=ApplyState.NOT_DECIDE;
    }

    public ApplyPrimaryKey getApplicationForCoursePK() {
        return applicationForCoursePK;
    }

    public void setApplicationForCoursePK(ApplyPrimaryKey applicationForCoursePK) {
        this.applicationForCoursePK = applicationForCoursePK;
    }

    public ApplyState getState() {
        return applyState;
    }

    public void setApplyState(ApplyState state) {
        this.applyState = state;
    }
}
