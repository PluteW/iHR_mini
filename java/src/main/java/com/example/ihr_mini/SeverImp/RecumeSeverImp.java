package com.example.ihr_mini.SeverImp;

import com.example.ihr_mini.Entity.Recume;
import com.example.ihr_mini.Mapper.RecumeMapper;
import com.example.ihr_mini.Sever.RecumeSever;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RecumeSeverImp implements RecumeSever {

    @Autowired
    private RecumeMapper recumeMapper;

    @Override
    public void addRecume(String id, String name, String graduate, String school) {
        recumeMapper.addRecume(id,name,graduate,school);
    }

    @Override
    public Recume getById(String id){
        return recumeMapper.getById(id);
    }

    @Override
    public List<Recume> getResumes() {
        return recumeMapper.getResumes();
    }

    @Override
    public List<String> getGraduates(){
        return recumeMapper.getGraduates();
    }

    @Override
    public String getId(String name) {
        return recumeMapper.getId(name);
    }

    @Override
    public String getName(String id) {
        return recumeMapper.getName(id);
    }

    @Override
    public String getGraduate(String id) {
        return recumeMapper.getGraduate(id);
    }
    @Override
    public String getSchool(String id) {
        return recumeMapper.getSchool(id);
    }

    @Override
    public int getState(String id) {
        return recumeMapper.getState(id);
    }

    @Override
    public String getOperator(String id) {
        return recumeMapper.getOperator(id);
    }

    @Override
    public void setId(String id) {
    }

    @Override
    public void setName(String name, String id) {
        recumeMapper.setName(name,id);
    }

    @Override
    public void setGraduate(String graduate, String id) {
        recumeMapper.setGraduate(graduate,id);
    }

    @Override
    public void setSchool(String school, String id) {
        recumeMapper.setSchool(school,id);
    }

    @Override
    public void setState(int state, String id) {
        recumeMapper.setState(state,id);
    }

    @Override
    public void setOperator(String operator, String id) {
        recumeMapper.setOperator(operator,id);
    }
}
