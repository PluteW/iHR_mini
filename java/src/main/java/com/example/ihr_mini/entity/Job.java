package com.example.ihr_mini.Entity;

import org.springframework.stereotype.Component;

@Component
public class Job {

    private String id; // 岗位代号
    private String name; // 岗位名称
    private String category; // 岗位类别
    private String location; // 工作地点
    private String scheduleTime; // 到岗时间
    private int account; // 招聘目标人数
    private String timeBegin; // 招聘开始时间
    private String timeEnd; // 招聘结束时间
    private String salary; // 岗位薪水待遇 0面议
    private String state; // 岗位状态码
    private String more; // 关于工作的更多需求

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public void setTimeBegin(String timeBegin) {
        this.timeBegin = timeBegin;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public int getAccount() {
        return account;
    }

    public String getTimeBegin() {
        return timeBegin;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public String getSalary() {
        return salary;
    }

    public String getState() {
        return state;
    }

    public String getMore() {
        return more;
    }

}
