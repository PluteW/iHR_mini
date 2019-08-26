package com.example.ihr_mini.Mapper;

import com.example.ihr_mini.Entity.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    public List<Test> getInfor();
    public Test getone(String id);
    public int addone(Test test);

}
