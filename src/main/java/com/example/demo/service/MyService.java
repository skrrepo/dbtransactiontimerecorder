package com.example.demo.service;

import com.example.demo.annotation.LogExecutionTime;
import com.example.demo.dao.JdbcDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Shreyas K R on 30-09-2018.
 */
@Service
public class MyService {

    @Qualifier("jdbcDao")
    @Autowired
    private JdbcDao jdbcDao;

    @LogExecutionTime
    public String getName(String id){
        return jdbcDao.getFirstName(id) +  jdbcDao.getLastName(id);
    }

    public String getNameWithoutLogging(String id){
        return jdbcDao.getFirstName(id) +  jdbcDao.getLastName(id);
    }
}
