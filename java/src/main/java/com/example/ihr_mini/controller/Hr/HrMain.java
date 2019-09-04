package com.example.ihr_mini.Controller.Hr;

import com.example.ihr_mini.SeverImp.HrMainSeverImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class HrMain {

    @Autowired
    private HrMainSeverImp hrMainSeverImp;

    //发布新岗位页面  初始化
    //更新可选择列表
    @PostMapping(path = "/hr/addNew/init")
    public Map addNewInit(){
        return hrMainSeverImp.addNewInit();
    }

    //发布新岗位  发布
    @PostMapping(path = "/hr/addNew/add")
    public boolean addNew(@RequestParam Map<String,Object> data){
        return hrMainSeverImp.addNew(data);
    }

    //查询页面初始化
    //用于对表单可选项进行初始化
    @PostMapping(path = "/hr/poilling/init")
    public Map poillingInit(){
        return hrMainSeverImp.poillingInit();
    }

    //查询页面接收提交
    //用于对表单查询的数据进行处理并返回数据
    @PostMapping(path = "/hr/poilling/submit")
    public Map poillingSubmit(@RequestParam Map<String,Object> data){
        return hrMainSeverImp.poiling(data);
    }

    //详情页面初始化
    //用于获取选中人员的pdf路径
    @PostMapping(path = "/hr/detail")
    public Map getDetails(@RequestParam Map<String,String> data){
        return hrMainSeverImp.pdfUrlDetail(data.get("employeeId"));
    }


}
