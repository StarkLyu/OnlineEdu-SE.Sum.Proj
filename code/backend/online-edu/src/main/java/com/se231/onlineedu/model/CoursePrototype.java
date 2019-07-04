package com.se231.onlineedu.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 * CoursePrototype Class.
 *
 * Prototype of course,store the resource of a course.
 *
 * @author Zhe Li
 *
 * @date 2019/7/3
 */
@Entity
@Table(name = "coursePrototype")
public class CoursePrototype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    private String description;

    @OneToMany(mappedBy = "coursePrototype")
    private List<Question> questions;

    @OneToMany(mappedBy = "coursePrototype")
    private List<Course> courses;

    public CoursePrototype(@NotBlank String title, String description, List<Question> questions, List<Course> courses, @NotBlank int state) {
        this.title = title;
        this.description = description;
        this.questions = questions;
        this.courses = courses;
        this.state = state;
    }

    public CoursePrototype() {

    }
    /**
     *  state is used to represent the state of a course prototype
     *  0:waiting for examined
     *  1:passed examined
     *  -1:not pass
     */
    @NotNull
    private int state;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
