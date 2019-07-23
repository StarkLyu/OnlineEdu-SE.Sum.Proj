package com.se231.onlineedu.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * @author yuxuanLiu
 * @date 2019/07/22
 */
@Embeddable
public class NoticePrimaryKey implements Serializable {

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private int noticeNo;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(int noticeNo) {
        this.noticeNo = noticeNo;
    }
}
