package com.example.ihr_mini.SeverImp;

import com.example.ihr_mini.Entity.Candidate;
import com.example.ihr_mini.Mapper.CandidateMapper;
import com.example.ihr_mini.Mapper.CodeStateMapper;
import com.example.ihr_mini.Sever.CandidateSever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HrPoillingSeverImp implements CandidateSever {

    @Autowired
    private CandidateMapper candidateMapper;
    @Autowired
    private CodeStateMapper codeStateMapper;

    //查询页面的菜单初始化
    public Map poilingInit(){
        Map<String,Object> res = new HashMap<>();

        List<String> region = candidateMapper.getRegions(); // 地区
        List<String> timeDB = candidateMapper.getTimes(); // 投递时间 yyyyMMdd格式
        ArrayList<String> time = new ArrayList<>();
        List<String> jobs = candidateMapper.getJobs(); // 已投岗位列表
        List<String> stateId = candidateMapper.getStates(); //可供选择的状态码 16位
        ArrayList<String> state = new ArrayList<>();
        List<Integer> age = candidateMapper.getAges(); // 可选年龄
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
        Map<String,Object> Range = this.poilingInit();
        List<String> regionRange = candidateMapper.getRegions(); // 地区
        List<String> timeRange = candidateMapper.getTimes(); // 投递时间 yyyyMMdd格式
        List<String> jobsRange = candidateMapper.getJobs(); // 已投岗位列表
        List<String> stateRangeId = candidateMapper.getStates(); //可供选择的状态码 16位
        List<Integer> ageRange = candidateMapper.getAges(); // 可选年龄
        List<String> eduBackGroundRangeId = candidateMapper.getEduBackGrounds();//可选教育背景

        System.out.println(regionRange+"  "+timeRange+"  "+jobsRange+"  "+stateRangeId+"  "+"  "+ageRange+
                "  "+eduBackGroundRangeId);
        String name = nameIn;
        String region = (regionIn == -1)? "":regionRange.get(regionIn);
        String job = (jobIn == -1)? "":jobsRange.get(jobIn);
        String time = (timeIn == -1)? "":timeRange.get(timeIn).substring(0,6); // 201908格式
        String stateId = (stateIn == -1)? "":stateRangeId.get(stateIn);
        int age = (ageIn == -1)? -1:ageRange.get(ageIn);
        String edubackGroundId = (edubackGroundIn == -1)? "":eduBackGroundRangeId.get(edubackGroundIn);
        int sex = sexIn; // 性别判断 -1都 1女 2男 3未知
        //当前页码，第一次查询时为0
        int pageIndex = pageIndexIn;

        System.out.println(name+"   "+region+"   "+job+"   "+time+"   "+stateId+"   "+age+"   "+edubackGroundId+"   "+sex+"   "+pageIndex);
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

    //根据具体情况来查询数据
    public List<Candidate> getByCondation(Map<Object,Object> params){
        return candidateMapper.getByCondation(params);
    }

    @Override
    public void addCandidate(String id,String mail,String pass){
        candidateMapper.addCandidate(id,mail,pass);
    }

    @Override
    public Candidate getCandidateById(String id){
        return  candidateMapper.getById(id);
    }

    @Override
    public List<String> getCandidateStates(){
        return candidateMapper.getStates();
    }

    @Override
    public List<Integer> getCandidateAges(){
        return candidateMapper.getAges();
    }

    @Override
    public List<Candidate> getCandidates() {
        return candidateMapper.getCandidates();
    }

    @Override
    public List<String> getCandidateRegions(){
        return candidateMapper.getRegions();
    }

    @Override
    public List<String> getCandidateMails(){
        return candidateMapper.getMails();
    }

    @Override
    public List<String> getCandidateNames(){
        return candidateMapper.getNames();
    }

    @Override
    public List<String> getCandidateEduBackGrounds(){
        return candidateMapper.getEduBackGrounds();
    }

    @Override
    public List<String> getCandidateGraduates(){
        return candidateMapper.getGraduates();
    }

    @Override
    public List<String> getCandidateTimes(){
        return candidateMapper.getTimes();
    }

    @Override
    public List<String> getCandidateJobs(){
        return candidateMapper.getJobs();
    }

    @Override
    public List<String> getCandidateJobRegions(){
        return candidateMapper.getJobRegions();
    }

    @Override
    public List<String> getCandidateJobIds(){
        return candidateMapper.getJobIds();
    }

    @Override
    public String getCandidateGraduate(String id){
        return candidateMapper.getGraduate(id);
    }

    @Override
    public void setCandidateGraduate(String graduate,String id){
        return;
    }

    @Override
    public void setCandidateTime(String time,String id) {
        return;
    }

    @Override
    public void setCandidateJob(String job,String id) {
        return;
    }

    @Override
    public void setCandidateJob_region(String job_region,String id) {
        return;
    }

    @Override
    public String getCandidateTime(String id) {
        return candidateMapper.getTime(id);
    }

    @Override
    public String getCandidateJob(String id) {
        return candidateMapper.getJob(id);
    }

    @Override
    public String getCandidateJob_region(String id) {
        return candidateMapper.getJob_region(id);
    }

    @Override
    public void setEduBackGroundId(String eduBackGroundId,String id){
        candidateMapper.setEduBackGroundId(eduBackGroundId,id);
    }

    @Override
    public String getEduBackGroundId(String id){
        return candidateMapper.getEduBackGroundId(id);
    }

    @Override
    public void setCandidateId(String id) {
    }

    @Override
    public void setCandidateMail(String mail,String id) {
        candidateMapper.setEduBackGroundId(mail,id);
    }

    @Override
    public void setCandidatePass(String pass,String id) {

        candidateMapper.setPass(pass,id);
    }

    @Override
    public void setCandidateName(String name,String id) {

        candidateMapper.setName(name,id);
    }

    @Override
    public void setCandidateState(String state,String id) {

        candidateMapper.setState(state,id);
    }

    @Override
    public void setCandidateOperaterId(String operaterId,String id) {

        candidateMapper.setOperaterId(operaterId,id);
    }

    @Override
    public void setCandidatePhone(String phone,String id) {

        candidateMapper.setPhone(phone,id);
    }

    @Override
    public void setCandidatePicId(String picId,String id) {

        candidateMapper.setPicId(picId,id);
    }

    @Override
    public void setCandidateAge(int age,String id) {

        candidateMapper.setAge(age,id);
    }

    @Override
    public void setCandidateBirthday(String birthday,String id) {

        candidateMapper.setBirthday(birthday,id);
    }

    @Override
    public void setCandidateResumeId(String resumeId,String id) {

        candidateMapper.setResumeId(resumeId,id);
    }

    @Override
    public void setCandidateSchool(String school,String id) {

        candidateMapper.setSchool(school,id);
    }

    @Override
    public void setCandidateMajor(String major,String id) {

        candidateMapper.setMajor(major,id);
    }

    @Override
    public void setCandidateAcademic(String academic,String id) {

        candidateMapper.setAcademic(academic,id);
    }

    @Override
    public void setCandidateEduBackGround(String eduBackGround,String id) {

        candidateMapper.setEduBackGround(eduBackGround,id);
    }

    @Override
    public void setCandidateHometown(String hometown,String id) {

        candidateMapper.setHometown(hometown,id);
    }

    @Override
    public void setCandidateOwnTbId(String ownTbId,String id) {

        candidateMapper.setOwnTbId(ownTbId,id);
    }


    @Override
    public void setCandidateJobId(String jobId,String id){
        candidateMapper.setJobId(jobId,id);
    }

    @Override
    public int getCandidateSex(String id){
        return candidateMapper.getSex(id);
    }

    @Override
    public void setCandidateSex(int sex,String id){
        candidateMapper.setSex(sex,id);
    }

    @Override
    public String getCandidateJobId(String id){
        return candidateMapper.getJobId(id);
    }

    @Override
    public String getCandidateId(String mail) {
        return candidateMapper.getId(mail);
    }

    @Override
    public String getCandidateMail(String id) {
        return candidateMapper.getMail(id);
    }

    @Override
    public String getCandidatePass(String id) {
        return candidateMapper.getPass(id);
    }

    @Override
    public String getCandidateName(String id) {
        return candidateMapper.getName(id);
    }

    @Override
    public String getCandidateState(String id) {
        return candidateMapper.getState(id);
    }

    @Override
    public String getCandidateOperaterId(String id) {
        return candidateMapper.getOperaterId(id);
    }

    @Override
    public String getCandidatePhone(String id) {
        return candidateMapper.getPhone(id);
    }

    @Override
    public String getCandidatePicId(String id) {
        return candidateMapper.getPicId(id);
    }

    @Override
    public int getCandidateAge(String id) {
        return candidateMapper.getAge(id);
    }

    @Override
    public String getCandidateBirthday(String id) {
        return candidateMapper.getBirthday(id);
    }

    @Override
    public String getCandidateResumeId(String id) {
        return candidateMapper.getResumeId(id);
    }

    @Override
    public String getCandidateSchool(String id) {
        return candidateMapper.getSchool(id);
    }

    @Override
    public String getCandidateMajor(String id) {
        return candidateMapper.getMajor(id);
    }

    @Override
    public String getCandidateAcademic(String id) {
        return candidateMapper.getAcademic(id);
    }

    @Override
    public String getCandidateEduBackGround(String id) {
        return candidateMapper.getEduBackGround(id);
    }

    @Override
    public String getCandidateHometown(String id) {
        return candidateMapper.getHometown(id);
    }

    @Override
    public String getCandidateOwnTbId(String id) {
        return candidateMapper.getOwnTbId(id);
    }
}
