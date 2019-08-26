package com.example.ihr_mini.SeverImp;

import com.example.ihr_mini.Entity.Hr;
import com.example.ihr_mini.Mapper.HrMapper;
import com.example.ihr_mini.Sever.HrSever;
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
    public String getName(String id) {
        return hrMapper.getName(id);
    }

    @Override
    public String getUsername(String id) {
        return hrMapper.getUsername(id);
    }

    @Override
    public String getPass(String id) {
        return hrMapper.getPass(id);
    }

    @Override
    public String getId(String username) {
        return hrMapper.getId(username);
    }

    @Override
    public String getPhone(String id) {
        return hrMapper.getPhone(id);
    }

    @Override
    public int getState(String id) {
        return hrMapper.getState(id);
    }

    @Override
    public String getJob(String id) {
        return hrMapper.getJob(id);
    }
}
