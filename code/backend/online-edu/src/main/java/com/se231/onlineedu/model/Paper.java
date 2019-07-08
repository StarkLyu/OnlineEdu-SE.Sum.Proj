package com.se231.onlineedu.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date start;

    private Date end;

    @OneToMany
    private List<PaperWithQuestions> questions;

    @ManyToOne
    private Course course;

    public Paper(Date start, Date end, List<PaperWithQuestions> questions) {
        this.start = start;
        this.end = end;
        this.questions = questions;
    }

    public Paper(Date start) {
        this.start = start;
    }

    public Paper() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public List<PaperWithQuestions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<PaperWithQuestions> questions) {
        this.questions = questions;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
