package com.example.ihr_mini.SeverImp;

import com.example.ihr_mini.Entity.Candidate;
import com.example.ihr_mini.Mapper.CandidateMapper;
import com.example.ihr_mini.Sever.CandidateSever;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CandidateSeverImp implements CandidateSever {

    @Autowired
    private CandidateMapper candidateMapper;

    @Override
    public void addCandidate(String id,String mail,String pass){
        candidateMapper.addCandidate(id,mail,pass);
    }

    @Override
    public Candidate getById(String id){
        return  candidateMapper.getById(id);
    }

    @Override
    public List<Candidate> getCandidates() {
        return candidateMapper.getCandidates();
    }

    @Override
    public void setTime(String time,String id) {
        return;
    }

    @Override
    public void setJob(String job,String id) {
        return;
    }

    @Override
    public void setJob_region(String job_region,String id) {
        return;
    }

    @Override
    public String getTime(String id) {
        return candidateMapper.getTime(id);
    }

    @Override
    public String getJob(String id) {
        return candidateMapper.getJob(id);
    }

    @Override
    public String getJob_region(String id) {
        return candidateMapper.getJob_region(id);
    }

    @Override
    public void setId(String id) {
        return;
    }

    @Override
    public void setMail(String mail,String id) {
        return;
    }

    @Override
    public void setPass(String pass,String id) {
        return;
    }

    @Override
    public void setName(String name,String id) {
        return;
    }

    @Override
    public void setState(int state,String id) {
        return;
    }

    @Override
    public void setOperaterId(String operaterId,String id) {
        return;
    }

    @Override
    public void setPhone(String phone,String id) {
        return;
    }

    @Override
    public void setPicId(String picId,String id) {
        return;
    }

    @Override
    public void setAge(int age,String id) {
        return;
    }

    @Override
    public void setBirthday(String birthday,String id) {
        return;
    }

    @Override
    public void setResumeId(String resumeId,String id) {
        return;
    }

    @Override
    public void setSchool(String school,String id) {
        return;
    }

    @Override
    public void setMajor(String major,String id) {
        return;
    }

    @Override
    public void setAcademic(String academic,String id) {
        return;
    }

    @Override
    public void setEduBackGround(String eduBackGround,String id) {
        return;
    }

    @Override
    public void setHometown(String hometown,String id) {
        return;
    }

    @Override
    public void setOwnTbId(String ownTbId,String id) {
        return;
    }


    @Override
    public void setJobId(String jobId,String id){
        return;
    }

    @Override
    public String getJobId(String id){
        return candidateMapper.getJobId(id);
    }

    @Override
    public String getId(String mail) {
        return candidateMapper.getId(mail);
    }

    @Override
    public String getMail(String id) {
        return candidateMapper.getMail(id);
    }

    @Override
    public String getPass(String id) {
        return candidateMapper.getPass(id);
    }

    @Override
    public String getName(String id) {
        return candidateMapper.getName(id);
    }

    @Override
    public int getState(String id) {
        return candidateMapper.getState(id);
    }

    @Override
    public String getOperaterId(String id) {
        return candidateMapper.getOperaterId(id);
    }

    @Override
    public String getPhone(String id) {
        return candidateMapper.getPhone(id);
    }

    @Override
    public String getPicId(String id) {
        return candidateMapper.getPicId(id);
    }

    @Override
    public int getAge(String id) {
        return candidateMapper.getAge(id);
    }

    @Override
    public String getBirthday(String id) {
        return candidateMapper.getBirthday(id);
    }

    @Override
    public String getResumeId(String id) {
        return candidateMapper.getResumeId(id);
    }

    @Override
    public String getSchool(String id) {
        return candidateMapper.getSchool(id);
    }

    @Override
    public String getMajor(String id) {
        return candidateMapper.getMajor(id);
    }

    @Override
    public String getAcademic(String id) {
        return candidateMapper.getAcademic(id);
    }

    @Override
    public String getEduBackGround(String id) {
        return candidateMapper.getEduBackGround(id);
    }

    @Override
    public String getHometown(String id) {
        return candidateMapper.getHometown(id);
    }

    @Override
    public String getOwnTbId(String id) {
        return candidateMapper.getOwnTbId(id);
    }
}
