package com.example.ihr_mini.Mapper;

import com.example.ihr_mini.Entity.Recume;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecumeMapper {

    public void addRecume(String id,String name,String graduate,String school);

    public Recume getById(String id);

    public List<Recume> getResumes();

    public String getId(String name);

    public List<String> getGraduates();

    public String getName(String id);

    public String getGraduate(String id);

    public String getSchool(String id);

    public int getState(String id);

    public String getOperator(String id);

    public void setId(String id);

    public void setName(String name,String id);

    public void setGraduate(String graduate,String id);

    public void setSchool(String school,String id);

    public void setState(int state,String id);

    public void setOperator(String operator,String id);
}
