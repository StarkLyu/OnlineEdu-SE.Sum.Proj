package com.se231.onlineedu.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * Excel Import Class
 *
 * this class is used to bulk import from excel
 * order:username,password,email,tel,university,major,sno,grade,real name,sex
 *
 * @author Zhe Li
 *
 * @date 2019/07/10
 */
public class UserExcel extends BaseRowModel {
    @ExcelProperty(index = 0,value = "username")
    private String username;

    @ExcelProperty(index = 1,value = "password")
    private String password;

    @ExcelProperty(index = 2,value = "email")
    private String email;

    @ExcelProperty(index = 3)
    private Long tel;

    @ExcelProperty(index = 4,value = "university")
    private String university;

    @ExcelProperty(index = 5,value = "major")
    private String major;

    @ExcelProperty(index = 6,value = "sno")
    private String sno;

    @ExcelProperty(index = 7)
    private int grade;

    @ExcelProperty(index = 8,value = "real name")
    private String realName;

    @ExcelProperty(index = 9,value = "sex")
    private String sex;

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

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
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
        sex = sex;
    }
}
