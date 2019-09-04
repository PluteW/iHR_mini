package com.example.ihr_mini.Controller.Employee;

import com.example.ihr_mini.SeverImp.EmployeeMainSeverImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class EmployeeMain {

    @Autowired
    private EmployeeMainSeverImp employeeMainSeverImp;

    // 查询页面初始化
    @PostMapping(path = "/employee/poilling/init")
    public Map poillingInit(){
        return employeeMainSeverImp.poillingInit();
    }


    //查询页面提交请求处理
    @PostMapping(path = "/employee/poilling/submit")
    public Map submit(@RequestParam Map data){{
        return employeeMainSeverImp.poilling(data);
    }}

    //详情页面初始化
    @PostMapping(path = "/employee/detail/getInfor")
    public Map getDetail(@RequestParam Map data){{
        return employeeMainSeverImp.getDetail(data);
    }}

    @PostMapping(path = "/emloyee/myjobs/init")
    public Map getMyJOBs(@RequestParam Map data){
        return employeeMainSeverImp.getMyJOBs(data);
    }
}
