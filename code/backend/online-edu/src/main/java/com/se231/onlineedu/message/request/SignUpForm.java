package com.se231.onlineedu.message.request;

import java.util.List;

import javax.validation.constraints.*;

/**
 * SignUpForm Class
 *
 * Sign up request form
 *
 * @author Yuxuan Liu
 *
 * @date 2019/07/01
 */
public class SignUpForm {
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @Email
    private String email;

    @Pattern(regexp = "0?(13|14|15|18|17)[0-9]{9}")
    private String tel;

    private String university;

    private String major;

    private int grade;

    private String sno;

    private String realName;

    private String sex;

    public SignUpForm(@NotBlank @Size(min = 3, max = 50) String username, List<String> roles, @NotBlank @Size(min = 6, max = 40) String password) {
        this.username = username;
        this.password = password;
    }

    public SignUpForm() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}