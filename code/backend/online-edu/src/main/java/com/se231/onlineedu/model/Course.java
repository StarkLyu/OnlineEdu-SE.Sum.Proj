package com.se231.onlineedu.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

/**
 * Course Class
 *
 * Course class is the main class to manage course information.
 *
 * @author zhe li
 * @date 2019/7/1
 */
@Entity
@Table(name = "Courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private Date startDate;

    @NotBlank
    private Date endDate;

    @NotBlank
    private int state;

    @ManyToOne(fetch = FetchType.LAZY)
    private CoursePrototype coursePrototype;

    @OneToMany(mappedBy = "course")
    private Section section;

    public Course(@NotBlank Date startDate, @NotBlank Date endDate, @NotBlank int state, CoursePrototype coursePrototype, Section section) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.state = state;
        this.coursePrototype = coursePrototype;
        this.section = section;
    }

    public Course() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public CoursePrototype getCoursePrototype() {
        return coursePrototype;
    }

    public void setCoursePrototype(CoursePrototype coursePrototype) {
        this.coursePrototype = coursePrototype;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }
}
