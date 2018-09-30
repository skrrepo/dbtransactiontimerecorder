package com.example.demo.profiler;

import com.example.demo.aspect.LogAspect;
import com.example.demo.dao.DataBaseTimeMaintainer;
import com.example.demo.dao.JdbcDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by Shreyas K R on 30-09-2018.
 */

public class JdbcProfiler implements MethodInterceptor {

    private Object delegate;



    DataBaseTimeMaintainer dataBaseTimeMaintainer;

    public JdbcProfiler(JdbcDao jdbcDao,DataBaseTimeMaintainer dataBaseTimeMaintainer) {
        this.delegate = jdbcDao;
        this.dataBaseTimeMaintainer = dataBaseTimeMaintainer;
    }


    @Override
    public Object intercept(Object proxy, Method method, Object[] methodArguments, MethodProxy methodProxy) throws InvocationTargetException, IllegalAccessException {
            Object result = null;


        if (method.getDeclaringClass() == Object.class) {
            try {
                return method.invoke(delegate, methodArguments);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        if(dataBaseTimeMaintainer.getRecordTime().get(Thread.currentThread().getId())!=null){
            StopWatch sw = new StopWatch(getClass().getSimpleName());
            sw.start();
            result = method.invoke(delegate, methodArguments);
            sw.stop();
            long time = dataBaseTimeMaintainer.getRecordTime().get(Thread.currentThread().getId());
            time = time + sw.getTotalTimeMillis();
            dataBaseTimeMaintainer.getRecordTime().put(Thread.currentThread().getId(),time);
        }
        else {
            result = method.invoke(delegate, methodArguments);

        }

       // System.out.println(sw.getTotalTimeMillis());
        return result;
    }
}
