package com.example.demo.aspect;

import com.example.demo.dao.DataBaseTimeMaintainer;
import com.example.demo.dao.JdbcDao;
import com.example.demo.profiler.JdbcProfiler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by Shreyas K R on 30-09-2018.
 */

@Aspect
@Component
public class LogAspect {

    @Qualifier("jdbcDao")
    @Autowired
    JdbcDao jdbcDao;

   @Autowired
   DataBaseTimeMaintainer dataBaseTimeMaintainer;

    @Around("@annotation(com.example.demo.annotation.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        dataBaseTimeMaintainer.getRecordTime().put(Thread.currentThread().getId(),0L);
        Object proceed = joinPoint.proceed();

        System.out.println(joinPoint.getSignature() + " executed in " + dataBaseTimeMaintainer.getRecordTime().get(Thread.currentThread().getId()) + "ms");
        dataBaseTimeMaintainer.getRecordTime().remove(Thread.currentThread().getId());
        return proceed;
    }

}
