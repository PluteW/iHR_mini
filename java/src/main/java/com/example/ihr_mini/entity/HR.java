package com.example.ihr_mini.Entity;

import org.springframework.stereotype.Component;

/**
 * 对应数据库里的t_hr_infor表，用于保存hr的数据
 */
@Component
public class Hr {

    private String username;
    private String name;
    private String password;
    private  String id;
    private String phone;
    private  String email;
    private int state;
    private  String job;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getState() {
        return state;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setState(int state) {
        this.state = state;
    }

}
