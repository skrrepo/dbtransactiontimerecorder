package com.example.demo.dao;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by Shreyas K R on 30-09-2018.
 */
@Component
public class DataBaseTimeMaintainer {
    private HashMap<Long,Long> recordTime = new HashMap<>();

    public HashMap<Long, Long> getRecordTime() {
        return recordTime;
    }
}
