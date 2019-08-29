package com.example.ihr_mini.SeverImp;

import com.example.ihr_mini.Entity.Job;
import com.example.ihr_mini.Mapper.JobMapper;
import com.example.ihr_mini.Sever.JobSever;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeverImp  implements JobSever {

    private JobMapper jobMapper;

    @Override
    public void addJob(String name, String category ,String location,String ScheduleTime,
                       String timeBegin,String timeEnd,int account, String salary, String state, String more){
        jobMapper.addJob(name,category, location, ScheduleTime, timeBegin, timeEnd, account, salary,state, more);
    }


    @Override
    public List<Job> getJobs() {
        return jobMapper.getJobs();
    }

    @Override
    public Job getById(String id) {
        return jobMapper.getById(id);
    }

    @Override
    public List<String> getIds(){
        return jobMapper.getIds();
    }

    @Override
    public List<String> getCategorys(){
        return jobMapper.getCategorys();
    }

    @Override
    public List<String> getLocations(){
        return jobMapper.getLocations();
    }

    @Override
    public List<String> getScheduleTimes(){
        return jobMapper.getScheduleTimes();
    }

    @Override
    public List<String> getTimBegins(){
        return jobMapper.getTimBegins();
    }

    @Override
    public List<String> getTimeEnds(){
        return jobMapper.getTimeEnds();
    }


    @Override
    public void setName(String name, String id) {
        return;
    }

    @Override
    public void setLocation(String location, String id) {
        return;
    }

    @Override
    public void setScheduleTime(String scheduleTime, String id) {
        return;
    }

    @Override
    public void setAccount(int account, String id) {
        return;
    }

    @Override
    public void setTimeBegin(String timeBegin, String id) {
        return;
    }

    @Override
    public void setTimeEnd(String timeEnd, String id) {
        return;
    }

    @Override
    public void setSalary(String salary, String id) {
        return;
    }

    @Override
    public void setState(String state, String id) {
        return;
    }

    @Override
    public void setMore(String more, String id) {
        return;
    }

    @Override
    public void setSex(int sex, String id) {
        return;
    }

    @Override
    public void setCategory(String category,String id){
        return;
    }

    @Override
    public String getCategory(String id){
        return jobMapper.getCategory(id);
    }

    @Override
    public String getId(String name) {
        return jobMapper.getId(name);
    }

    @Override
    public String getName(String id) {
        return jobMapper.getName(id);
    }

    @Override
    public String getLocation(String id) {
        return jobMapper.getLocation(id);
    }

    @Override
    public String getScheduleTime(String id) {
        return jobMapper.getScheduleTime(id);
    }

    @Override
    public int getAccount(String id) {
        return jobMapper.getAccount(id);
    }

    @Override
    public String getTimeBegin(String id) {
        return jobMapper.getTimeBegin(id);
    }

    @Override
    public String getTimeEnd(String id) {
        return jobMapper.getTimeEnd(id);
    }

    @Override
    public String getSalary(String id) {
        return jobMapper.getSalary(id);
    }

    @Override
    public String getState(String id) {
        return jobMapper.getState(id);
    }

    @Override
    public String getMore(String id) {
        return jobMapper.getMore(id);
    }

    @Override
    public int getSex(String id) {
        return jobMapper.getSex(id);
    }
}
