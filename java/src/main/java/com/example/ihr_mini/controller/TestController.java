package com.example.ihr_mini.Controller;

import com.example.ihr_mini.Entity.Test;
import com.example.ihr_mini.Sever.TestSever;
import com.example.ihr_mini.TestSeverImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private TestSeverImpl testSever;
    @Autowired
    private Test test;

    @RequestMapping("get")
    public List<Test> getinfor(){
        return testSever.getInfor();
    }

    @RequestMapping("add/{id}")
    public List<Test> addone(@PathVariable String id){
        test.setId(id);
        test.setName("测试1");
        testSever.addone(test);
        return testSever.getInfor();
    }
    @RequestMapping("get/{id}")
    public Test getone(@PathVariable String id){
        return testSever.getone(id);
    }
}
