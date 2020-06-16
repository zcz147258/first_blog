package com.it.person.Controller;

import com.it.person.Annotation.UserLoginToken;
import com.it.person.Service.GetinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class GetInfoController {
    @Autowired
    GetinfoService getinfoService;
    @GetMapping("/getinfo")
    public Map<String,Object> findAll(@RequestParam("username") String username,
                                      @RequestParam("type") String type){
        System.out.println("进入getinfo");
        return getinfoService.findInfo(username,type);
    }
}
