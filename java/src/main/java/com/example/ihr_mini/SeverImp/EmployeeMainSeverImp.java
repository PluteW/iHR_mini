package com.example.ihr_mini.SeverImp;

import com.example.ihr_mini.Entity.Job;
import com.example.ihr_mini.Mapper.CandidateMapper;
import com.example.ihr_mini.Mapper.CodeStateMapper;
import com.example.ihr_mini.Mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EmployeeMainSeverImp {

    @Autowired
    private JobMapper jobMapper;
    @Autowired
    private CodeStateMapper codeStateMapper;
    @Autowired
    private CandidateMapper candidateMapper;

    //employee页面
    //查询菜初始化
    public Map poillingInit(){
        Map<String,Object> res = new HashMap<>();

        //全部的可选择的工作岗位列表
        List<Job> jobs = jobMapper.getJobs();
        Job job = null;
        String tem = "";
        for (int i = 0;i<jobs.size();i++){
            job = jobs.get(i);
            tem = job.getLocation();
            job.setLocation(codeStateMapper.getStatementById(tem));
            tem = job.getSalary();
            job.setSalary(codeStateMapper.getStatementById(tem));
            tem = job.getState();
            job.setState(codeStateMapper.getStatementById(tem));
        }
        res.put("informationData",jobs);

        //可选项列表更新
        //获得可选的岗位类别的
        List<String> JobCategorys = jobMapper.getCategorys();
        //获得可选的工作地点的
        List<String> Loctions = jobMapper.getLocations();
        //获得可供选择的教育背景
        List<String> eduBackGrounds = jobMapper.geteduBackGrounds();
        //获得可供选择的状态码
        List<String> states= jobMapper.getstates();

        //以下将id转为文本描述
        String statement = "";
        for(int i = 0;i<JobCategorys.size();i++){
            tem = JobCategorys.get(i); // 获得一个id
            statement = codeStateMapper.getStatementById(tem);
            if(statement == null){
                statement = "暂定";
            }
            JobCategorys.set(i,statement);
        }
        for(int i = 0;i<Loctions.size();i++){
            tem = Loctions.get(i); // 获得一个id
            statement = codeStateMapper.getStatementById(tem);
            if(statement == null){
                statement = "未确定";
            }
            Loctions.set(i,statement);
        }
        for(int i = 0;i<eduBackGrounds.size();i++){
            tem = eduBackGrounds.get(i); // 获得一个id
            statement = codeStateMapper.getStatementById(tem);
            if(statement == null){
                statement = "未做要求";
            }
            eduBackGrounds.set(i,statement);
        }
        for(int i = 0;i<states.size();i++){
            tem = states.get(i); // 获得一个id
            statement = codeStateMapper.getStatementById(tem);
            states.set(i,statement);
        }

        System.out.println(JobCategorys+"    "+Loctions+"    "+eduBackGrounds+"    "+states);
        res.put("region",Loctions);
        res.put("eduBackGround",eduBackGrounds);
        res.put("state",states);
        res.put("jobRegin",JobCategorys);

        return  res;
    }

    //数据查询
    public Map poilling(Map data){
        Map<String,Object> res = new HashMap<>();

        String name = (String)data.get("form[name]");
        int locationIn = (data.get("form[region]") == "")?-1:Integer.parseInt((String)data.get("form[region]"));
        boolean doEmail = (data.get("form[emailRemind]") == "false")?false:true;
        int jobRegionIn = (data.get("form[jobRegin]") == "")?-1:Integer.parseInt((String)data.get("form[jobRegin]"));
        int stateIn = (data.get("form[state]") == "")?-1:Integer.parseInt((String)data.get("form[state]"));
        int educationIn = (data.get("form[eduBackGround]") == "")?-1:Integer.parseInt((String)data.get("form[eduBackGround]"));

        Map dataRange = this.getRanges();

        String location = (locationIn == -1)?"":((List<String>) dataRange.get("region")).get(locationIn);
        String jobRegin = (jobRegionIn == -1)?"":((List<String>) dataRange.get("jobRegin")).get(jobRegionIn);
        String state = (stateIn == -1)?"":((List<String>) dataRange.get("state")).get(stateIn);
        String eduBackGround = (educationIn == -1)?"":((List<String>) dataRange.get("eduBackGround")).get(educationIn);

        String locationID = (codeStateMapper.getIdByStatement(location) == null)?"-1":codeStateMapper.getIdByStatement(location);
        String jobReginID = (codeStateMapper.getIdByStatement(jobRegin) == null)?"-1":codeStateMapper.getIdByStatement(jobRegin);
        String stateID = (codeStateMapper.getIdByStatement(state) == null)?"-1":codeStateMapper.getIdByStatement(state);
        String eduBackGroundID = (codeStateMapper.getIdByStatement(eduBackGround) == null)?"-1":codeStateMapper.getIdByStatement(eduBackGround);


        Date date = null;
        String d = "";
        SimpleDateFormat sdfDB = new SimpleDateFormat("yyyyMM");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String scheduleDate = (String)data.get("form[scheduleTime]");
        if(scheduleDate.equals("")){
        }else {
            try {
                date = sdf.parse(scheduleDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            d = sdfDB.format(date);
        }

        Map<Object,Object> params = new HashMap<>();
        params.put("c_name",name);
        params.put("c_location",locationID);
        params.put("c_category",jobReginID);
        params.put("c_state",stateID);
        params.put("c_ScheduleTime",d);
        params.put("c_eduBackGround",eduBackGroundID);
        System.out.println(params);
        List<Job> jobs = this.getByCondation(params);

        Job job = null;
        String tem = "";
        for (int i = 0;i<jobs.size();i++){
            job = jobs.get(i);
            tem = job.getLocation();
            job.setLocation(codeStateMapper.getStatementById(tem));
            tem = job.getSalary();
            job.setSalary(codeStateMapper.getStatementById(tem));
            tem = job.getState();
            job.setState(codeStateMapper.getStatementById(tem));
        }
        res.put("informationData",jobs);
        return res;
    }

    //获取表单的范围
    public Map getRanges(){
        Map<String,Object> res = new HashMap<>();

        String tem = "";
        //可选项列表更新
        //获得可选的岗位类别的
        List<String> JobCategorys = jobMapper.getCategorys();
        //获得可选的工作地点的
        List<String> Loctions = jobMapper.getLocations();
        //获得可供选择的教育背景
        List<String> eduBackGrounds = jobMapper.geteduBackGrounds();
        //获得可供选择的状态码
        List<String> states= jobMapper.getstates();

        //以下将id转为文本描述
        String statement = "";
        for(int i = 0;i<JobCategorys.size();i++){
            tem = JobCategorys.get(i); // 获得一个id
            statement = codeStateMapper.getStatementById(tem);
            if(statement == null){
                statement = "暂定";
            }
            JobCategorys.set(i,statement);
        }
        for(int i = 0;i<Loctions.size();i++){
            tem = Loctions.get(i); // 获得一个id
            statement = codeStateMapper.getStatementById(tem);
            if(statement == null){
                statement = "未确定";
            }
            Loctions.set(i,statement);
        }
        for(int i = 0;i<eduBackGrounds.size();i++){
            tem = eduBackGrounds.get(i); // 获得一个id
            statement = codeStateMapper.getStatementById(tem);
            if(statement == null){
                statement = "未做要求";
            }
            eduBackGrounds.set(i,statement);
        }
        for(int i = 0;i<states.size();i++){
            tem = states.get(i); // 获得一个id
            statement = codeStateMapper.getStatementById(tem);
            states.set(i,statement);
        }

        res.put("region",Loctions);
        res.put("eduBackGround",eduBackGrounds);
        res.put("state",states);
        res.put("jobRegin",JobCategorys);

        return  res;
    }

    //按照条件查询
    public List<Job> getByCondation(Map<Object,Object> params){
        return jobMapper.getByCondation(params);
    }

    //详情页面查询
    public Map getDetail(Map data){

        String jobID = (String)data.get("jobId");
        Map<String,Object> res = new HashMap<>();
        Job job = getJobById(jobID);
        res.put("jobData",job);
        return  res;
    }

    //获得当前账号投递过的岗位
    public Map getMyJOBs(Map data){
        Map<String,Object> res = new HashMap<>();
        String employeeID = (String)data.get("employeeId");
        ArrayList<Job> jobs = new ArrayList<>();
        List<String> jobIDs = candidateMapper.getJobIdsByEmail(employeeID);
        System.out.println(jobIDs);
        Job job = null;
        for(int i = 0;i<jobIDs.size();i++){
            job = getJobById(jobIDs.get(i));
            jobs.add(job);
        }
        res.put("informationData",jobs);
        return res;
    }

    //根据岗位的ID返回详细信息
    public Job getJobById(String jobID){
        Job job = jobMapper.getById(jobID);
        if(job != null){
            //代号转文本
            job.setState(codeStateMapper.getStatementById(job.getState()));  // 状态
            job.setCategory(codeStateMapper.getStatementById(job.getCategory())); // 岗位类别
            job.setEduBackGround(codeStateMapper.getStatementById(job.getEduBackGround())); // 教育背景
            job.setLocation(codeStateMapper.getStatementById(job.getLocation())); // 工作地点
            job.setSalary(codeStateMapper.getStatementById(job.getSalary())); // 薪资待遇

            String timeBegin = job.getTimeBegin();
            String timeEnd = job.getTimeEnd();
            String scheduleTime =job.getScheduleTime();
            //月份格式转换
            Date date = null;
            String d = "";
            SimpleDateFormat sdfDB = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            //三个日期格式的转换
            try {
                if(timeBegin.equals("0")){
                    job.setTimeBegin("即日起");
                } else {
                    date = sdfDB.parse(timeBegin);
                    d = sdf.format(date);
                    job.setTimeBegin(d);
                }
                if(timeEnd.equals("0")){
                    job.setTimeEnd("长期");
                }else {
                    date = sdfDB.parse(timeEnd);
                    d = sdf.format(date);
                    job.setTimeEnd(d);
                }
                if(scheduleTime.equals("0")){
                    job.setScheduleTime("尽早到岗");
                }else {
                    date = sdfDB.parse(scheduleTime);
                    d = sdf.format(date);
                    job.setScheduleTime(d);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        return job;
    }
}
