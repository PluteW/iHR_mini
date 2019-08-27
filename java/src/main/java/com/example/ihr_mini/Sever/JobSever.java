package com.example.ihr_mini.Sever;

import com.example.ihr_mini.Entity.Job;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface JobSever {

    public void addJob(String name,String location,String ScheduleTime,String timeBegin,String timeEnd,int sex,int account, String salary, String more);


    public List<Job> getJobs();

    public Job getById(String id);

    public void setName(String name,String id);

    public void setLocation(String location,String id);

    public void setScheduleTime(String scheduleTime,String id);

    public void setAccount(int account,String id);

    public void setTimeBegin(String timeBegin,String id);

    public void setTimeEnd(String timeEnd,String id);

    public void setSalary(String salary,String id);

    public void setState(int state,String id);

    public void setMore(String more,String id);

    public void setSex(int sex,String id);

    public String getId(String name);

    public String getName(String id);

    public String getLocation(String id);

    public String getScheduleTime(String id);

    public int getAccount(String id);

    public String getTimeBegin(String id);

    public String getTimeEnd(String id);

    public String getSalary(String id);

    public int getState(String id);

    public String getMore(String id);

    public int getSex(String id);
}
