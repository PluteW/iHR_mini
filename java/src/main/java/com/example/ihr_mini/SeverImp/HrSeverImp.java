package com.example.ihr_mini.Sever;

import com.example.ihr_mini.Entity.Hr;
import com.example.ihr_mini.Mapper.HrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HrSeverImp implements HrSever {

    @Autowired
    private HrMapper hrMapper;


    @Override
    public List<Hr> getHrs() {
        return hrMapper.getHrs();
    }

    @Override
    public Hr getName(String id) {
        return hrMapper.getName(id);
    }

//    @Override
//    public Hr getUsername(String id) {
//        return hrMapper.getUsername(id);
//    }
//
//    @Override
//    public Hr getPass(String id) {
//        return hrMapper.getPass(id);
//    }
//
//    @Override
//    public Hr getId(String username) {
//        return hrMapper.getId(username);
//    }
//
//    @Override
//    public Hr getPhone(String id) {
//        return hrMapper.getPhone(id);
//    }
//
//    @Override
//    public Hr getState(String id) {
//        return hrMapper.getState(id);
//    }
//
//    @Override
//    public Hr getJob(String id) {
//        return hrMapper.getJob(id);
//    }
}
