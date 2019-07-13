package com.se231.onlineedu.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Paper Entity Class
 *
 * paper is a list of questions which generate a test or a exercise.
 *
 * @author Zhe Li
 *
 * @date 2019/7/10
 */
@ApiModel("试卷,作业的实体类")
@Entity
public class Paper {
    @ApiModelProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("开始时间")
    private Date start;

    @ApiModelProperty("结束时间")
    private Date end;

    @ApiModelProperty("试卷的习题集")
    @OneToMany
    private List<PaperWithQuestions> questions;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public Paper(Date start, Date end, List<PaperWithQuestions> questions) {
        this.start = start;
        this.end = end;
        this.questions = questions;
    }

    public Paper(Date start) {
        this.start = start;
    }

    public Paper() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public List<PaperWithQuestions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<PaperWithQuestions> questions) {
        this.questions = questions;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
