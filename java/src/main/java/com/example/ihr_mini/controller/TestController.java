package com.example.ihr_mini.Controller;

import com.example.ihr_mini.Entity.Hr;
import com.example.ihr_mini.SeverImp.HrSeverImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private HrSeverImp hrSeverImp;

    @RequestMapping("hr")
    public List<Hr> gethrs(){
        return hrSeverImp.getHrs();
    }
    @RequestMapping("one")
    public String one(){
        return hrSeverImp.getJob("482957");
    }
    @RequestMapping("two")
    public int two(){
        return hrSeverImp.getState("482957");
    }
}
