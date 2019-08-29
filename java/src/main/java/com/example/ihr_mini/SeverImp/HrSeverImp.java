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
    public List<Hr> getPushHrList(){
        return hrMapper.getPushHrList();
    }

    @Override
    public Hr getById(String id){
        return hrMapper.getById(id);
    }

    @Override
    public List<String> getNames(){
        return hrMapper.getNames();
    }

    @Override
    public List<String> getIds(){
        return hrMapper.getIds();
    }

    @Override
    public List<String> getMails(){
        return hrMapper.getMails();
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

    @Override
    public String getPicId(String id){
        return  hrMapper.getPicId(id);
    }

    @Override
    public void setPicId(String picId,String id){
        return;
    }
}
