package com.example.demo.controller;

import com.example.demo.dao.JdbcDao;
import com.example.demo.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Shreyas K R on 30-09-2018.
 */

@RestController
public class MyController {

    @Autowired
    private MyService myService;

    @GetMapping("/name")
    public String getName(){
        return myService.getName("1");
    }

    @GetMapping("/nameWithoutLog")
    public String getNameWithoutLog(){
        return myService.getNameWithoutLogging("1");
    }
}
