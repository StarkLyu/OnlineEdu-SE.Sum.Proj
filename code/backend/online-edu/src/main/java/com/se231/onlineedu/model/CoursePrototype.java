package com.se231.onlineedu.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Prototype of course,store the resource of a course.
 */
@Entity
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

    @NotBlank
    private int state;

    public CoursePrototype(@NotBlank String title, String description, List<Question> questions, List<Course> courses, @NotBlank int state) {
        this.title = title;
        this.description = description;
        this.questions = questions;
        this.courses = courses;
        this.state = state;
    }

    public CoursePrototype() {
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
