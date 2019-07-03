package com.se231.onlineedu.model;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class PaperAnswerPrimaryKey {
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "paper_id")
    private Paper paper;

    private int times;

    public PaperAnswerPrimaryKey(User user, Paper paper, int times) {
        this.user = user;
        this.paper = paper;
        this.times = times;
    }

    public PaperAnswerPrimaryKey(User user) {
        this.user = user;
    }

    public PaperAnswerPrimaryKey() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
