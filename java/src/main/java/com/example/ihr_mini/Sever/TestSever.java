package com.example.ihr_mini.Sever;

import com.example.ihr_mini.Entity.Test;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestSever {
    public List<Test> getInfor();
    public Test getone(String id);
    public int addone(Test test);
}
