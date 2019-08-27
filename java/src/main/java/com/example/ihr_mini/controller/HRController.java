package com.example.ihr_mini.Controller;

import com.example.ihr_mini.Entity.Hr;
import com.example.ihr_mini.Mapper.CandidateMapper;
import com.example.ihr_mini.SeverImp.HrSeverImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HRController {

    @Autowired
    private HrSeverImp hrSeverImp;
    @Autowired
    private CandidateMapper candidateMapper;

    @RequestMapping("hr")
    public List<Hr> gethrs(){
        return hrSeverImp.getHrs();
    }
    @RequestMapping("hrs")
    public void geths(){
        candidateMapper.setAcademic("软件工程学院","12345678");
    }
}
