package com.example.ihr_mini.Sever;

import com.example.ihr_mini.Entity.Candidate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

@Service
public interface CandidateSever {

    public List<Candidate> getByCondation(@Param("params") Map<Object,Object> params);

    public void addCandidate(String id,String mail,String pass);

    public Candidate getCandidateById(String id);

    public List<Candidate> getCandidates();

    public List<String> getCandidateStates();

    public List<Integer> getCandidateAges();

    public List<String> getCandidateRegions();

    public List<String> getCandidateMails();

    public List<String> getCandidateNames();

    public List<String> getCandidateEduBackGrounds();

    public List<String> getCandidateGraduates();

    public List<String> getCandidateTimes();

    public List<String> getCandidateJobs();

    public List<String> getCandidateJobRegions();

    public List<String> getCandidateJobIds();

    public void setCandidateTime(String time,String id);

    public void setCandidateJob(String job,String id);

    public void setCandidateJob_region(String job_region,String id);

    public String getCandidateTime(String id);

    public String getCandidateJob(String id);

    public String getCandidateJob_region(String id);

    public void setCandidateId(String id);

    public String getCandidateGraduate(String id);

    public void setCandidateGraduate(String graduate,String id);

    public void setCandidateMail(String mail,String id);

    public void setCandidatePass(String pass,String id);

    public void setCandidateName(String name,String id);

    public void setCandidateState(String state,String id);

    public void setCandidateOperaterId(String operaterId,String id);

    public void setCandidatePhone(String phone,String id);

    public void setCandidatePicId(String picId,String id);

    public void setCandidateAge(int age,String id);

    public void setCandidateBirthday(String birthday,String id);

    public void setCandidateResumeId(String resumeId,String id);

    public void setCandidateSchool(String school,String id);

    public void setCandidateMajor(String major,String id);

    public void setCandidateAcademic(String academic,String id);

    public void setCandidateEduBackGround(String eduBackGround,String id);

    public void setCandidateHometown(String hometown,String id);

    public void setCandidateOwnTbId(String ownTbId,String id);

    public void setCandidateJobId(String jobId,String id);

    public void setEduBackGroundId(String eduBackGroundId,String id);

    public String getEduBackGroundId(String id);

    public String getCandidateJobId(String id);

    public int getCandidateSex(String id);

    public void setCandidateSex(int sex,String id);

    public String getCandidateId(String mail);

    public String getCandidateMail(String id);

    public String getCandidatePass(String id);

    public String getCandidateName(String id);

    public String getCandidateState(String id);

    public String getCandidateOperaterId(String id);

    public String getCandidatePhone(String id);

    public String getCandidatePicId(String id);

    public int getCandidateAge(String id);

    public String getCandidateBirthday(String id);

    public String getCandidateResumeId(String id);

    public String getCandidateSchool(String id);

    public String getCandidateMajor(String id);

    public String getCandidateAcademic(String id);

    public String getCandidateEduBackGround(String id);

    public String getCandidateHometown(String id);

    public String getCandidateOwnTbId(String id);
}
