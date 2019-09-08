package com.se231.onlineedu.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.sql.Date;

/**
 * @author Zhe Li
 * @date 2019/09/05
 */
@Embeddable
public class StudyRecordPrimaryKey implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Date studyDate;

    public StudyRecordPrimaryKey() {
    }

    public StudyRecordPrimaryKey(User user) {
        this.user = user;
        studyDate = new Date(new java.util.Date().getTime());
    }

    public StudyRecordPrimaryKey(User user, Date studyDate) {
        this.user = user;
        this.studyDate = studyDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getStudyDate() {
        return studyDate;
    }

    public void setStudyDate(Date studyDate) {
        this.studyDate = studyDate;
    }
}
