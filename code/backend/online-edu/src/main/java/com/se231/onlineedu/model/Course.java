package com.se231.onlineedu.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.poi.ss.usermodel.DateUtil;

/**
 * Course Class
 *
 * Course class is the main class to manage course information.
 *
 * @author zhe li
 * @date 2019/7/1
 */
@ApiModel(value = "课程，即基于课程原型衍生的实际的课程，有老师有学生")
@Entity
@Table(name = "Courses")
public class Course {
    @ApiModelProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "开始日期",required = true)
    @NotNull
    private Date startDate;

    @ApiModelProperty(value = "结束日期",required = true)
    @NotNull
    private Date endDate;

    @ApiModelProperty(value = "课程的状态",example = "有以下几个状态： APPLYING,READY_TO_START,TEACHING,FINISHED,NOT_PASS")
    @NotNull
    @Enumerated(EnumType.STRING)
    private CourseState state;

    @ApiModelProperty(value = "该课程基于的课程原型")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private CoursePrototype coursePrototype;

    @OneToMany(mappedBy = "sectionPrimaryKey.course")
    private List<Section> sections;

    @ApiModelProperty("课程名称")
    private String courseTitle;

    @ApiModelProperty("地点")
    private String location;

    @OneToMany
    @ApiModelProperty("上课时间段")
    private List<TimeSlot> timeSlots;

    @ApiModelProperty("选了该课程的学生")
    @JsonIgnore
    @ManyToMany(mappedBy = "courses")
    private Set<User> students;

    @ApiModelProperty("该课程的老师")
    @ManyToOne
    private User teacher;

    @ApiModelProperty("该课程的所有试卷")
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
        return teacher;
    }

    public void setUser(User user) {
        this.teacher = user;
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

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public Course(@NotNull Date startDate, @NotNull Date endDate, @NotNull CourseState state, CoursePrototype coursePrototype, User user) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.state = state;
        this.coursePrototype = coursePrototype;
        this.teacher = user;
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
