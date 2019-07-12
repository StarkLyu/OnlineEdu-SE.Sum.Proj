package com.se231.onlineedu.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CourseState state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private CoursePrototype coursePrototype;

    @OneToMany(mappedBy = "sectionPrimaryKey.course")
    private List<Section> sections;

    @JsonIgnore
    @ManyToMany(mappedBy = "courses")
    private Set<User> students;

    @OneToOne
    private User user;

    @OneToMany
    private List<Paper> papers;

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

    public Set<User> getStudents() {
        return students;
    }

    public void setStudents(Set<User> students) {
        this.students = students;
    }

    public List<Paper> getPapers() {
        return papers;
    }

    public void setPapers(List<Paper> papers) {
        this.papers = papers;
    }

    public Course(@NotNull Date startDate, @NotNull Date endDate, @NotNull CourseState state, CoursePrototype coursePrototype, User user) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.state = state;
        this.coursePrototype = coursePrototype;
        this.user = user;
    }

    public Course(@NotNull Date startDate, @NotNull Date endDate, @NotNull CourseState state, CoursePrototype coursePrototype, List<Section> sections) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.state = state;
        this.coursePrototype = coursePrototype;
        this.sections = sections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id.equals(course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
