package com.example.ihr_mini.Mapper;

import com.example.ihr_mini.Entity.Hr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HrMapper {

    public List<Hr> getHrs();

    public List<Hr> getPushHrList();

    public Hr getById(String id);

    public List<String> getNames();

    public List<String> getIds();

    public List<String> getMails();

    public String getName(String id);

    public String getUsername(String id);

    public String getPass(String id);

    public String getId(String username);

    public String getPhone(String id);

    public int getState(String id);

    public String getJob(String id);

    public String getPicId(String id);

    public void setPicId(String picId,String id);
}
