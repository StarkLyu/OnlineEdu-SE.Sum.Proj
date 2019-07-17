package com.se231.onlineedu.model;


import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

@Embeddable
public class SignInPrimaryKey {
    @OneToMany
    private Course course;

    private int signInNo = 0;

    public SignInPrimaryKey(Course course, int signInNo) {
        this.course = course;
        this.signInNo = signInNo;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getSignInNo() {
        return signInNo;
    }

    public void setSignInNo(int signInNo) {
        this.signInNo = signInNo;
    }
}
