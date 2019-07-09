package com.se231.onlineedu.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.se231.onlineedu.message.request.SignUpForm;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;




/**
 * User class
 *
 * User class is the main entity used to manage user info and control authorization
 *
 * @author Yuxuan Liu
 *
 * @date 2019/07/01
 */
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        })
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String username;

    @JsonIgnore
    @NotBlank
    private String password;

    @NotBlank
    private String email;

    private Long tel;

    private String university;

    private String major;

    private int grade;

    private String sno;

    private String realName;

    private String sex;



    @Column(name = "enabled")
    private boolean enabled;


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public User(){
        super();
        this.enabled = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(Long id, @NotBlank String username, @NotBlank String password, List<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "pick_course",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "courses_id"))
    private List<Course> courses;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public User(@NotBlank String username, @NotBlank String password) {
        this.username = username;
        this.password = password;
    }

    public User(SignUpForm form) {
        this.username = form.getUsername();
        this.email = form.getEmail();
        this.tel = Long.parseLong(form.getTel());
        this.university = form.getUniversity();
        this.major = form.getMajor();
        this.grade = form.getGrade();
        this.sno = form.getSno();
        this.realName = form.getRealName();
        this.sex = form.getSex();
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
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
