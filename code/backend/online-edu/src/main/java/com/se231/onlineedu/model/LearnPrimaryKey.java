package com.se231.onlineedu.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class LearnPrimaryKey implements Serializable {

    @ManyToOne
    private User student;

    @ManyToOne
    private Course course;

    public LearnPrimaryKey(User student, Course course) {
        this.student = student;
        this.course = course;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
