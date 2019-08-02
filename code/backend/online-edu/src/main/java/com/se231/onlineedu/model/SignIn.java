package com.se231.onlineedu.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class SignIn {
    @EmbeddedId
    private SignInPrimaryKey signInPrimaryKey;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endDate;

    @ManyToMany
    private List<User> users;

    public SignIn(Course course, int signInNo, Date startDate, Date endDate) {
        signInPrimaryKey = new SignInPrimaryKey(course, signInNo);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public SignIn() {
    }

    public SignIn(SignInPrimaryKey signInPrimaryKey, Date startDate, Date endDate) {
        this.signInPrimaryKey = signInPrimaryKey;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public SignInPrimaryKey getSignInPrimaryKey() {
        return signInPrimaryKey;
    }

    public void setSignInPrimaryKey(SignInPrimaryKey signInPrimaryKey) {
        this.signInPrimaryKey = signInPrimaryKey;
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
