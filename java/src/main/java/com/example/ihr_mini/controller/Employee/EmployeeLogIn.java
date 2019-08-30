package com.example.ihr_mini.Controller.Employee;

import com.example.ihr_mini.SeverImp.EmployeeLogSeverImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin
public class EmployeeLogIn {

    @Autowired
    private EmployeeLogSeverImp employeeLogSeverImp;

    //Employee页面登录
    @PostMapping(path = "/employee/login/log")
    public Map log(@RequestParam Map data){
        return employeeLogSeverImp.login(data);
    }

    //Employee页面注册
    
}
