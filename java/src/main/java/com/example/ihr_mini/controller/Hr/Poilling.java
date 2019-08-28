package com.example.ihr_mini.Controller.Hr;

import com.example.ihr_mini.SeverImp.HrPoillingSeverImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin
public class Poilling {

    @Autowired
    private HrPoillingSeverImp hrPoillingSeverImp;

    @PostMapping(path = "/hr/poilling/init")
    public Map init(){
        return hrPoillingSeverImp.poilingInit();
    }
    @PostMapping(path = "/hr/poilling/submit")
    public Map submit(@RequestParam Map<String,Object> data){
        return hrPoillingSeverImp.poiling(data);
    }
}
