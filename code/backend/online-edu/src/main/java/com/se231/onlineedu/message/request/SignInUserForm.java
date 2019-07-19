package com.se231.onlineedu.message.request;

/**
 * @author liu
 */
public class SignInUserForm {
    private Long courseId;
    private int signInNo;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public int getSignInNo() {
        return signInNo;
    }

    public void setSignInNo(int signInNo) {
        this.signInNo = signInNo;
    }
}
