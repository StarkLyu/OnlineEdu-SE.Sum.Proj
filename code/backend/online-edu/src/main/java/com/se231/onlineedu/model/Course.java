package com.se231.onlineedu.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.List;

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
    @Enumerated(EnumType.STRING)
    private CourseState state;

    @ManyToOne(fetch = FetchType.LAZY)
    private CoursePrototype coursePrototype;

    @OneToMany(mappedBy = "sectionPrimaryKey.course")
    private List<Section> sections;

    @OneToOne
    private User user;

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

    public CourseState getState() {
        return state;
    }

    public void setState(CourseState state) {
        this.state = state;
    }

    public CoursePrototype getCoursePrototype() {
        return coursePrototype;
    }

    public void setCoursePrototype(CoursePrototype coursePrototype) {
        this.coursePrototype = coursePrototype;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course(@NotBlank Date startDate, @NotBlank Date endDate, @NotBlank CourseState state, CoursePrototype coursePrototype, List<Section> sections) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.state = state;
        this.coursePrototype = coursePrototype;
        this.sections = sections;
    }
}
