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
    private List<Question> questions;

    public Paper(Date start, Date end, List<Question> questions) {
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
