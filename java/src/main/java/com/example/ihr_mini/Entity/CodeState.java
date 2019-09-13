package com.example.ihr_mini.Entity;

public class CodeState {
    private String code; //大分类
    private String id; // 小分类
    private  String statement; // 具体描述

    public void setCode(String code) {
        this.code = code;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getCode() {
        return code;
    }

    public String getId() {
        return id;
    }

    public String getStatement() {
        return statement;
    }
}
