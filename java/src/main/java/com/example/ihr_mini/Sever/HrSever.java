package com.example.ihr_mini.Sever;

import com.example.ihr_mini.Entity.HR;
import com.example.ihr_mini.Mapper.HRMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HRSever {
    public List<HR> getHRs();

    public String getName(String id);

    public String getUsername(String id);

    public  String getPass(String id);

    public String getId(String username);

    public String getPhone(String id);

    public String getState(String id);

    public String getJob(String id);
}
