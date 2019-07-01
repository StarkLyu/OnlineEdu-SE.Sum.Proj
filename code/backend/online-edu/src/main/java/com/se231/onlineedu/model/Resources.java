package com.se231.onlineedu.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Resources Class
 *
 * Resources class is used to map the resource file contained in a specific course.
 *
 * @author zhe li
 * @date 2019/7/1
 */
public class Resources {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Course course;

    @NotBlank
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
