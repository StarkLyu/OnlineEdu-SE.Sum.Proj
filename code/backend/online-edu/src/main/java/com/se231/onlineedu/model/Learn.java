package com.se231.onlineedu.model;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Learn {
    @EmbeddedId
    private LearnPrimaryKey learnPrimaryKey;

    private int grade;

    private int signInTimes = 0;

    public Learn(LearnPrimaryKey learnPrimaryKey, int grade, int signInTimes) {
        this.learnPrimaryKey = learnPrimaryKey;
        this.grade = grade;
        this.signInTimes = signInTimes;
    }

    public Learn(User user,  Course course) {
        this.learnPrimaryKey = new LearnPrimaryKey(user, course);
    }

    public LearnPrimaryKey getLearnPrimaryKey() {
        return learnPrimaryKey;
    }

    public void setLearnPrimaryKey(LearnPrimaryKey learnPrimaryKey) {
        this.learnPrimaryKey = learnPrimaryKey;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getSignInTimes() {
        return signInTimes;
    }

    public void setSignInTimes(int signInTimes) {
        this.signInTimes = signInTimes;
    }
}
