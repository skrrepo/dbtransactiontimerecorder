package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by Shreyas K R on 30-09-2018.
 */
@Repository("jdbcObj")
public class JdbcDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public String getFirstName(String id){
        long startTime = System.currentTimeMillis();
         String result = jdbcTemplate.queryForObject("select FIRSTNAME from customer where id=?",new Object[]{id},String.class);
        System.out.println(System.currentTimeMillis()-startTime);
        return result;
    }

    public String getLastName(String id){
        long startTime = System.currentTimeMillis();
        String result = jdbcTemplate.queryForObject("select LASTNAME from customer where id=?",new Object[]{id},String.class);
        System.out.println(System.currentTimeMillis()-startTime);
        return result;
    }
}
