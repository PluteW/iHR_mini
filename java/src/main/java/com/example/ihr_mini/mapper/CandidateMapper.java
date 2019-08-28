package com.example.ihr_mini.Mapper;

import com.example.ihr_mini.Entity.Candidate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

@Mapper
public interface CandidateMapper {

    public List<Candidate> getByCondation(@Param("params") Map<Object,Object> params);

    public void addCandidate(String id,String mail,String pass);

    public Candidate getById(String id);

    public List<Candidate> getCandidates();

    public List<String> getRegions();

    public List<String> getStates();

    public List<Integer> getAges();

    public List<String> getMails();

    public List<String> getNames();

    public List<String> getEduBackGrounds();

    public List<String> getGraduates();

    public List<String> getTimes();

    public List<String> getJobs();

    public List<String> getJobRegions();

    public List<String> getJobIds();

    public void setTime(String time,String id);

    public void setJob(String job,String id);

    public void setJob_region(String job_region,String id);

    public void setEduBackGroundId(String eduBackGroundId,String id);

    public String getEduBackGroundId(String id);

    public String getTime(String id);

    public String getJob(String id);

    public String getJob_region(String id);

    public void setId(String id);

    public void setMail(String mail,String id);

    public void setPass(String pass,String id);

    public void setName(String name,String id);

    public void setState(String state,String id);

    public void setOperaterId(String operaterId,String id);

    public void setPhone(String phone,String id);

    public void setPicId(String picId,String id);

    public void setAge(int age,String id);

    public void setBirthday(String birthday,String id);

    public void setResumeId(String resumeId,String id);

    public void setSchool(String school,String id);

    public void setMajor(String major,String id);

    public void setAcademic(String academic,String id);

    public void setEduBackGround(String eduBackGround,String id);

    public void setHometown(String hometown,String id);

    public void setOwnTbId(String ownTbId,String id);

    public void setJobId(String jobId,String id);

    public void setSex(int sex,String id);

    public int getSex(String id);

    public String getGraduate(String id);

    public void setGraduate(String graduate,String id);

    public String getJobId(String id);

    public String getId(String mail);

    public String getMail(String id);

    public String getPass(String id);

    public String getName(String id);

    public String getState(String id);

    public String getOperaterId(String id);

    public String getPhone(String id);

    public String getPicId(String id);

    public int getAge(String id);

    public String getBirthday(String id);

    public String getResumeId(String id);

    public String getSchool(String id);

    public String getMajor(String id);

    public String getAcademic(String id);

    public String getEduBackGround(String id);

    public String getHometown(String id);

    public String getOwnTbId(String id);
}
