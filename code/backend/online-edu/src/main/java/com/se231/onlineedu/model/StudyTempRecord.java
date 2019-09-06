package com.se231.onlineedu.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Zhe Li
 * @date 2019/09/06
 */
@Entity
public class StudyTempRecord {
    @Id
    private long id;

    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;

    private VideoAction prevState;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date prevTime;

    public StudyTempRecord() {
    }

    public StudyTempRecord(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VideoAction getPrevState() {
        return prevState;
    }

    public void setPrevState(VideoAction prevState) {
        this.prevState = prevState;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getPrevTime() {
        return prevTime;
    }

    public void setPrevTime(Date prevTime) {
        this.prevTime = prevTime;
    }
}
