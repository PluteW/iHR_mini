package com.example.ihr_mini.Entity;

import org.springframework.stereotype.Component;

@Component
public class Recume {
    private String id; // 简历代号
    private String name; // 应聘者姓名
    private String graduate; // 毕业年份
    private String school; //  毕业学校
    private int state; // 简历状态号
    private String operator; // 操作者id

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGraduate() {
        return graduate;
    }

    public String getSchool() {
        return school;
    }

    public int getState() {
        return state;
    }

    public String getOperator() {
        return operator;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGraduate(String graduate) {
        this.graduate = graduate;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
