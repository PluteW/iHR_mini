package com.example.ihr_mini;

import com.example.ihr_mini.Entity.Test;
import com.example.ihr_mini.Mapper.TestMapper;
import com.example.ihr_mini.Sever.TestSever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestSeverImpl implements TestSever {

    @Autowired
    private TestMapper testMapper;

    @Override
    public List<Test> getInfor() {
        return testMapper.getInfor();
    }

    @Override
    public Test getone(String id) {
        return testMapper.getone(id);
    }

    @Override
    public int addone(Test test) {
        return testMapper.addone(test);
    }
}
