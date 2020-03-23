package com.example.ihr_mini.SeverImp;

import com.example.ihr_mini.Entity.Candidate;
import com.example.ihr_mini.Mapper.CandidateMapper;
import com.example.ihr_mini.Mapper.CodeStateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeLogSeverImp {

    @Autowired
    private CandidateMapper candidateMapper;
    @Autowired
    private CodeStateMapper codeStateMapper;

    //登录请求处理
    public Map login(Map data){
        Map<String,Object> res = new HashMap<>();
        int result = -1;
        String mail = (String)data.get("username");
        String passIn = (String)data.get("password");
        System.out.println(mail+ "    "+passIn);
        Candidate employeeUser = candidateMapper.getOneByMail(mail);
        if (employeeUser == null) { // 账号存在，进入密码判断
            result = -1;
            res.put("result",result);
            return res;
        } else {
            String passDB = employeeUser.getPass();
            System.out.println(passDB);
            System.out.println(passIn);
            if ((passDB).equals(passIn)){ // 密码正确，登录成功
                result = 2;
                res.put("result",result);

                String id = mail; // 应聘者以email作为区分标准
                String picId = employeeUser.getPicId(); // 获取数据库里的图片名称
                String name = employeeUser.getName();
                String stateId = employeeUser.getState();
                String stateStatement = codeStateMapper.getStatementById(stateId);
                int state = -1;
//                更新账号状态
                if (stateStatement.equals("正常")){
                    state = 0;
                }
//                将登陆用户的信息返回给前端
                Map<String,Object> userdata = new HashMap<>();
                userdata.put("id",id);
                userdata.put("picId",picId);
                userdata.put("name",name);
                userdata.put("state",state);

                res.put("userdata",userdata);
                System.out.println(res);
                return res;
            } else {
                result = 1;
                res.put("result",result);
                return res;
            }
        }
    }

    //注册请求处理
    public Map register(Map data){
        Map<String,Object> res = new HashMap<>();

        int result = -1;

        String mail = (String)data.get("username");
        String pass = (String)data.get("password");
        Candidate candidate = candidateMapper.getOneByMail(mail);
//        如果账号已经被人注册了
        if(candidate != null){
            result = -1;
            res.put("result",result);
            return res;
        }else {
//            账号没有被人注册，则进行正常的注册程序
            candidateMapper.addCandidate(mail,pass);
            candidate = candidateMapper.getOneByMail(mail);

            String id = mail; // 应聘者以email作为区分标准
            String picId = candidate.getPicId(); // 获取数据库里的图片名称
            String name = "未设置"; //新注册人员，姓名默认未设置
            String stateId = candidate.getState();// 获取状态码ID
            String stateStatement = codeStateMapper.getStatementById(stateId);  //状态码ID转文本描述

            int state = -1;
            if (stateStatement.equals("正常")){
                state = 0;
            }
            //初始化Map来存储用户信息
            Map<String,Object> userdata = new HashMap<>();
            userdata.put("id",id);
            userdata.put("picId",picId);
            userdata.put("name",name);
            userdata.put("state",state);

            res.put("userdata",userdata);
        }
        return res;
    }

}
