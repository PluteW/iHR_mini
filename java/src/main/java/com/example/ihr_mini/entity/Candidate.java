package com.example.ihr_mini.Entity;

import org.springframework.stereotype.Component;

@Component
public class Candidate {
    private String id; // 应聘者id
    private String mail; // 应聘者邮箱
    private String pass; // 应聘者
    private String name; // 姓名
    private String state; // 状态
    private String operaterId; //  操作者id
    private String phone; // 手机号
    private String picId; // 照片代号
    private int age; // 年龄
    private String birthday; //  出生年月日
    private String resumeId; // 简历代号
    private String graduate; // 毕业时间
    private String school; // 学校
    private String major; // 专业
    private String academic; // 学院
    private String eduBackGround; // 教育背景
    private String eduBackGroundId; // 教育背景代号
    private String hometown; // 地域
    private String ownTbId; // 详细信息表代号
    private String time; // 投递时间
    private String job; // 应聘岗位
    private String job_region; // 岗位类别
    private String jobId; // 岗位id
    private int sex; // 年龄 0 女 1 男 2未知


    public void setTime(String time) {
        this.time = time;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setJob_region(String job_region) {
        this.job_region = job_region;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setOperaterId(String operaterId) {
        this.operaterId = operaterId;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setAcademic(String academic) {
        this.academic = academic;
    }

    public void setEduBackGround(String eduBackGround) {
        this.eduBackGround = eduBackGround;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public void setOwnTbId(String ownTbId) {
        this.ownTbId = ownTbId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setEduBackGroundId(String eduBackGroundId) {
        this.eduBackGroundId = eduBackGroundId;
    }

    public String getEduBackGroundId() {
        return eduBackGroundId;
    }

    public int getSex() {
        return sex;
    }

    public String getJobId() {
        return jobId;
    }

    public String getGraduate() {
        return graduate;
    }

    public void setGraduate(String graduate) {
        this.graduate = graduate;
    }

    public String getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public String getPass() {
        return pass;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public String getOperaterId() {
        return operaterId;
    }

    public String getPhone() {
        return phone;
    }

    public String getPicId() {
        return picId;
    }

    public int getAge() {
        return age;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getResumeId() {
        return resumeId;
    }

    public String getSchool() {
        return school;
    }

    public String getMajor() {
        return major;
    }

    public String getAcademic() {
        return academic;
    }

    public String getEduBackGround() {
        return eduBackGround;
    }

    public String getHometown() {
        return hometown;
    }

    public String getOwnTbId() {
        return ownTbId;
    }

    public String getTime() {
        return time;
    }

    public String getJob() {
        return job;
    }

    public String getJob_region() {
        return job_region;
    }


}
