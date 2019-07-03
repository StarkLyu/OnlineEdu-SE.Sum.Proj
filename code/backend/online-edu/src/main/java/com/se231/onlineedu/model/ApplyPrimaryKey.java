package com.se231.onlineedu.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Primary Key Class for Apply
 *
 * Teacher Apply For Course Entity use two foreign key as its primary key.
 *
 * @author Zhe Li
 *
 * @date 2019/7/3
 */
@Embeddable
public class ApplyPrimaryKey implements Serializable {
    private static final Long serialVersionUID = 1L;

    @ManyToOne()
    private User teachingAdmin;

    @ManyToOne()
    private CoursePrototype coursePrototype;

    public ApplyPrimaryKey() {
    }

    public ApplyPrimaryKey(User teachingAdmin, CoursePrototype coursePrototype) {
        this.teachingAdmin = teachingAdmin;
        this.coursePrototype = coursePrototype;
    }

    public User getTeachingAdmin() {
        return teachingAdmin;
    }

    public void setTeachingAdmin(User teachingAdmin) {
        this.teachingAdmin = teachingAdmin;
    }

    public CoursePrototype getCoursePrototype() {
        return coursePrototype;
    }

    public void setCoursePrototype(CoursePrototype coursePrototype) {
        this.coursePrototype = coursePrototype;
    }
}
