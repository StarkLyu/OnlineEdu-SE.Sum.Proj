package com.se231.onlineedu.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonBackReference;

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

    @NotNull
    @Enumerated(EnumType.STRING)
    private CoursePrototypeState state;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public CoursePrototype() {}

    public CoursePrototype(@NotBlank String title, String description, List<Question> questions, List<Course> courses, @NotBlank CoursePrototypeState state) {
        this.title = title;
        this.description = description;
        this.questions = questions;
        this.courses = courses;
        this.state = state;
    }



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

    public CoursePrototypeState getState() {
        return state;
    }

    public void setState(CoursePrototypeState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoursePrototype that = (CoursePrototype) o;
        return id.equals(that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(questions, that.questions) &&
                Objects.equals(courses, that.courses) &&
                state == that.state &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, questions, courses, state, user);
    }
}
