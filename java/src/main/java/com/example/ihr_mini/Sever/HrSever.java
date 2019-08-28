package com.example.ihr_mini.Sever;

import com.example.ihr_mini.Entity.Hr;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HrSever {
    public List<Hr> getHrs();

    public Hr getById(String id);

    public List<String> getNames();

    public List<String> getIds();

    public List<String> getMails();

    public String getName(String id);

    public String getUsername(String id);

    public  String getPass(String id);

    public String getId(String username);

    public String getPhone(String id);

    public int getState(String id);

    public String getJob(String id);

    public String getPicId(String id);

    public void setPicId(String picId,String id);
}
