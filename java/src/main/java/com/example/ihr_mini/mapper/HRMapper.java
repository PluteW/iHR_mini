package com.example.ihr_mini.Mapper;

import com.example.ihr_mini.Entity.hr_person;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface hr_mapper {

    public List<hr_person> getHRs();

    public String getName(String id);

    public String getUsername(String id);

    public  String getPass(String id);

    public String getId(String name);

    public String getPhone(String id);

    public String getState(String id);

    public String getJob(String id);
}
