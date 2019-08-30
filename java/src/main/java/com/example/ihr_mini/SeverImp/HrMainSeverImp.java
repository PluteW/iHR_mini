package com.example.ihr_mini.SeverImp;

import com.example.ihr_mini.Entity.Candidate;
import com.example.ihr_mini.Entity.Hr;
import com.example.ihr_mini.Mapper.CandidateMapper;
import com.example.ihr_mini.Mapper.CodeStateMapper;
import com.example.ihr_mini.Mapper.HrMapper;
import com.example.ihr_mini.Mapper.JobMapper;
import com.example.ihr_mini.Sever.CandidateSever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HrMainSeverImp{

    @Autowired
    private CandidateMapper candidateMapper;
    @Autowired
    private CodeStateMapper codeStateMapper;
    @Autowired
    private HrMapper hrMapper;
    @Autowired
    private JobMapper jobMapper;

    // 发布新工作初始化
    public Map addNewInit(){
        Map<String,Object> res = new HashMap<>();
        List<String> region = codeStateMapper.getStatementsByCode("8MSVx6vA6nPCuBb4"); // 可选择的工作地点
        List<String> salaryOption = codeStateMapper.getStatementsByCode("oltPpyOVlMCYPlVJ"); // 可选择的薪资待遇
        List<String> jobCatetay = codeStateMapper.getStatementsByCode("zz94nDeo5PJjCimF"); // 可选择的岗位分类列表
        List<String> eduBackGround = codeStateMapper.getStatementsByCode("rkJcY90TpgIcjcVq"); // 可供选择的教育背景
        res.put("region",region);
        res.put("salaryOption",salaryOption);
        res.put("jobCategray",jobCatetay);
        res.put("eduBackGround",eduBackGround);
        return res;
    }

    //发布新岗位
    public boolean addNew(Map<String,Object> data){
        boolean res = false;
        //可选范围接收
        String[] regionRange = ((String)data.get("region")).substring(1,((String)data.get("region")).length()-1).split(",");
        String[] salaryRange = ((String)data.get("salaryOption")).substring(1,((String)data.get("salaryOption")).length()-1).split(",");
        String[] jobCategrayRange = ((String)data.get("jobCategray")).substring(1,((String)data.get("jobCategray")).length()-1).split(",");
        String[] eduBackGroundRange = ((String)data.get("eduBackGround")).substring(1,((String)data.get("eduBackGround")).length()-1).split(",");
        //岗位名接收
        String nameIn = (String)data.get("form[name]"); // 输入的岗位名
        //列表选择位的控制
        int regionIn = ((String)data.get("form[region]") == "")? -1:Integer.parseInt((String)data.get("form[region]")); // 选择的工作地点在可选范围中的id
        int jobCategrayIn = ((String)data.get("form[jobCategray]") == "")? -1:Integer.parseInt((String)data.get("form[jobCategray]")); // 选择的岗位分类在可选范围中的id
        int eduBackGroundIn = ((String)data.get("form[eduBackGround]") == "")? -1:Integer.parseInt((String)data.get("form[eduBackGround]")); // 选择的教育背景在可选范围中的id
        int salaryIn = ((String)data.get("form[salary]") == "")? -1:Integer.parseInt((String)data.get("form[salary]"));// 选择的薪资待遇在可选范围中的id
        int accountIn = ((String)data.get("form[account]") == "")? 1:Integer.parseInt((String)data.get("form[account]"));// 目标招聘人数
        //获取文本内容，并去掉两边的“”
        String region = (regionIn == -1)?" ":regionRange[regionIn].substring(1,regionRange[regionIn].length()-1);
        String jobCategray = (jobCategrayIn == -1)?"":jobCategrayRange[jobCategrayIn].substring(1,jobCategrayRange[jobCategrayIn].length()-1);
        String eduBackGround = (eduBackGroundIn == -1)?"":eduBackGroundRange[eduBackGroundIn].substring(1,eduBackGroundRange[eduBackGroundIn].length()-1);
        String salary = (salaryIn == -1)?"":salaryRange[salaryIn].substring(1,salaryRange[salaryIn].length()-1);
        System.out.println(region+"  "+jobCategray+"  "+eduBackGround+"  "+salary);
        //获取对应的代号
        String regionID = codeStateMapper.getCodeByStatement(region);
        String categrayID = codeStateMapper.getCodeByStatement(jobCategray);
        String eduBackGroundID = codeStateMapper.getCodeByStatement(eduBackGround);
        String salaryID = codeStateMapper.getCodeByStatement(salary);
        System.out.println(regionID+"  "+categrayID+"  "+eduBackGroundID+"  "+salaryID);

        //日期输入类的接收
        String timeBeginIn = (String)data.get("form[timeBegin]"); // 招聘开始时间
        String timeEndIn = (String)data.get("form[timeEnd]"); // 招聘结束时间
        String scheduleTimeIn = (String)data.get("form[scheduleTime]"); // 到岗时间
        // 三个日期格式的转变
        SimpleDateFormat sdfDB = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            timeBeginIn = (timeBeginIn=="")?"0":sdfDB.format(sdf.parse(timeBeginIn));
            timeEndIn = (timeEndIn=="")?"0":sdfDB.format(sdf.parse(timeEndIn));
            scheduleTimeIn = (scheduleTimeIn=="")?"0":sdfDB.format(sdf.parse(scheduleTimeIn));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String stateID = "N2935px4ZjuYc0ga"; // 暂时默认为状态为投递中
        String more = ""; // 暂时默认增加的数据为空
        jobMapper.addJob(nameIn, categrayID, regionID, scheduleTimeIn, timeBeginIn, timeEndIn, accountIn,salaryID,stateID,more);
        res = true;
        return res;
    }

    //查询页面的菜单初始化
    public Map poillingInit(){
        Map<String,Object> res = new HashMap<>();

        List<String> region = candidateMapper.getRegions(); // 地区
        List<String> timeDB = candidateMapper.getTimes(); // 投递时间 yyyyMMdd格式
        Collections.sort(timeDB);
        ArrayList<String> time = new ArrayList<>();
        List<String> jobs = candidateMapper.getJobs(); // 已投岗位列表
        List<String> stateId = candidateMapper.getStates(); //可供选择的状态码 16位
        ArrayList<String> state = new ArrayList<>();
        List<Integer> age = candidateMapper.getAges(); // 可选年龄
        Collections.sort(age);
        List<String> eduBackGround = candidateMapper.getEduBackGrounds();//可选教育背景
        //月份格式转换
        Date date = null;
        String d = "";
        SimpleDateFormat sdfDB = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
        try {
            for(int i = 0;i<timeDB.size();i++){
                date = sdfDB.parse(timeDB.get(i));
                d = sdf.format(date);
                if (!time.contains(d)){
                    time.add(d);
                }else{
                    continue;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //状态码转文字
        for(int i = 0;i<stateId.size();i++){
            d = codeStateMapper.getStatementById(stateId.get(i));
            if (!state.contains(d)){
                state.add(d);
            }else{
                continue;
            }
        }
        res.put("region",region);
        res.put("time",time);
        res.put("jobRegin",jobs);
        res.put("jobs",jobs);
        res.put("state",state);
        res.put("age",age);
        res.put("eduBackGround",eduBackGround);
        return res;
    }

    //查询页面，查询
    public Map poiling(Map<String,Object> data){
        Map<String,Object> res = new HashMap<>();
        String nameIn = (String)data.get("form[name]");
        int regionIn = ((String)data.get("form[region]") == "") ? -1:Integer.parseInt((String)data.get("form[region]"));
        int sexIn = ((String)data.get("form[sex]") == "") ? -1:Integer.parseInt((String)data.get("form[sex]"));
        int timeIn = ((String)data.get("form[time]") == "") ? -1:Integer.parseInt((String)data.get("form[time]"));
        int edubackGroundIn = ((String)data.get("form[eduBackGround]") == "") ? -1:Integer.parseInt((String)data.get("form[eduBackGround]"));
        int jobIn = ((String)data.get("form[jobRegin]") == "") ? -1:Integer.parseInt((String)data.get("form[jobRegin]"));
        int stateIn = ((String)data.get("form[status]") == "") ? -1:Integer.parseInt((String)data.get("form[status]"));
        int ageIn =((String)data.get("form[age]") == "") ? -1: Integer.parseInt((String)data.get("form[age]"));

        int pageIndexIn = Integer.parseInt((String)data.get("pageIndex"));

        //可选择列表列表
        Map<String,Object> Range = this.poillingInit();
        List<String> regionRange = candidateMapper.getRegions(); // 地区
        List<String> times = candidateMapper.getTimes(); // 投递时间 yyyyMMdd格式
        ArrayList<String> timeRange = new ArrayList<>();
        //月份格式转换
        Date date = null;
        String d = "";
        SimpleDateFormat sdfDB = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月");
        SimpleDateFormat sdfB= new SimpleDateFormat("yyyyMM");
        try {
            for(int i = 0;i<times.size();i++){
                date = sdfDB.parse(times.get(i));
                d = sdf.format(date);
                if (!timeRange.contains(d)){
                    timeRange.add(d);
                }else{
                    continue;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String time = "";
        if(timeIn == -1){
            time = "";
        }else{
            try {
                time =timeRange.get(timeIn);
                date = sdf.parse(time);
                time = sdfB.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        String name = nameIn;
        List<String> jobsRange = candidateMapper.getJobs(); // 已投岗位列表
        List<String> stateRangeId = candidateMapper.getStates(); //可供选择的状态码 16位
        List<Integer> ageRange = candidateMapper.getAges(); // 可选年龄
        List<String> eduBackGroundRangeId = candidateMapper.getEduBackGrounds();//可选教育背景
        String region = (regionIn == -1)? "":regionRange.get(regionIn);
        String job = (jobIn == -1)? "":jobsRange.get(jobIn);
        String stateId = (stateIn == -1)? "":stateRangeId.get(stateIn);
        int age = (ageIn == -1)? -1:ageRange.get(ageIn);
        String edubackGroundId = (edubackGroundIn == -1)? "":eduBackGroundRangeId.get(edubackGroundIn);
        int sex = sexIn; // 性别判断 -1都 1女 2男 3未知
        //当前页码，第一次查询时为0
        int pageIndex = pageIndexIn;

        Map<Object,Object> params = new HashMap<>();
        params.put("c_name",name);
        params.put("c_native",region);
        params.put("c_job",job);
        params.put("c_time",time);
        params.put("c_state",stateId);
        params.put("c_age",age);
        params.put("c_edubackGround",edubackGroundId);
        params.put("c_sex",sex);
        List<Candidate> tableData = this.getByCondation(params);
        int resultTotal = tableData.size();
        res.put("tableData",tableData);
        res.put("resultTotal",resultTotal);
        return res;
    }

    //查询应聘者的pdf路径
    public Map pdfUrlDetail(String id){
        Map<String,Object> res = new HashMap<>();
        String resumeId = candidateMapper.getResumeId(id);
        String pdfUrl = "static/pdf/"+resumeId+".pdf";

        res.put("pdfUrl",pdfUrl);

        String stateId = candidateMapper.getState(id);
        String stateStr = codeStateMapper.getCodeById(stateId);
        boolean unlocked = true;
        if (stateStr == "锁定"){
            unlocked = false;
        }
        res.put("unlocked",unlocked);

        List<Hr> tableData = hrMapper.getPushHrList();

        res.put("tableData",tableData);

        return res;
    }

    //根据具体情况来查询数据
    public List<Candidate> getByCondation(Map<Object,Object> params){
        return candidateMapper.getByCondation(params);
    }
}
