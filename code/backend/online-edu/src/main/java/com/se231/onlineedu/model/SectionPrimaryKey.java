package com.se231.onlineedu.model;

import javax.persistence.*;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * SectionRepository Primary Key Class
 *
 * a section must be identified by a course an its no
 *
 * @author Yuxuan Liu
 *
 * @date 2019/7/4
 */
@Embeddable
public class SectionPrimaryKey implements Serializable {
    private static final Long serialVersionUID = 1L;

    @JsonBackReference
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int secNo;

    public SectionPrimaryKey(Course course,int secNo){
        this.course=course;
        this.secNo=secNo;
    }

    public SectionPrimaryKey() {
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getSecNo() {
        return secNo;
    }

    public void setSecNo(int secNo) {
        this.secNo = secNo;
    }
}
