package com.example.ihr_mini.Controller.Hr;

import com.example.ihr_mini.Entity.Hr;
import com.example.ihr_mini.Mapper.HrMapper;
import com.example.ihr_mini.SeverImp.HrSeverImp;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class HRLogin {

    @Autowired
    private HrSeverImp hrSeverImp;
    @Autowired
    private Hr hr;

    @PostMapping(path = "/hr/login")
    public Map login(@RequestParam Map<String,String> data){
        Map<String,Object> res = new HashMap<>(); // 定义空map用于保存数据
        String usernameIn = data.get("username"); // 获取用户名
        String passIn = data.get("password"); // 获取输入的密码
        String id = hrSeverImp.getId(usernameIn); // 尝试获取用户id
        if(id == null){
            res.put("result",false);
            return res;
        }else{
            String passDB = hrSeverImp.getPass(id).toString();
            System.out.println("passDB    "+passDB);
            System.out.println("passIn    "+passIn);
            if(!(passDB).equals(passIn)){
                res.put("result",false);
                return res;
            }else {
                hr = hrSeverImp.getById(id);
                res.put("result",true);
                res.put("userdata",hr);
                return res;
            }
        }
    }
}
