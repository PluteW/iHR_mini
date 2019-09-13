package com.example.ihr_mini.Mapper;

import com.example.ihr_mini.Entity.Job;
import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface JobMapper {

    public List<Job> getByCondation(@Param("params") Map<Object,Object> params);

    public void addJob(String name, String category ,String location,String ScheduleTime,String timeBegin,
                       String timeEnd,int account, String salary, String state, String more);

    public List<Job> getJobs();

    public Job getById(String id);

    public List<String> getIds();

    public List<String> getCategorys();

    public List<String> getLocations();

    public List<String> geteduBackGrounds();

    public List<String> getstates();

    public List<String> getScheduleTimes();

    public List<String> getTimBegins();

    public List<String> getTimeEnds();

    public void setName(String name,String id);

    public void setLocation(String location,String id);

    public void setScheduleTime(String scheduleTime,String id);

    public void setAccount(int account,String id);

    public void setTimeBegin(String timeBegin,String id);

    public void setTimeEnd(String timeEnd,String id);

    public void setSalary(String salary,String id);

    public void setState(String state,String id);

    public void setMore(String more,String id);

    public void setSex(int sex,String id);

    public void setCategory(String category,String id);

    public void setEduBackGround(String eduBackGround,String id);

    public String getEduBackGround(String id);

    public String getCategory(String id);

    public String getId(String name);

    public String getName(String id);

    public String getLocation(String id);

    public String getScheduleTime(String id);

    public int getAccount(String id);

    public String getTimeBegin(String id);

    public String getTimeEnd(String id);

    public String getSalary(String id);

    public String getState(String id);

    public String getMore(String id);

    public int getSex(String id);
}
