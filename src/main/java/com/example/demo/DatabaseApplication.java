package com.example.demo;

import com.example.demo.aspect.LogAspect;
import com.example.demo.dao.DataBaseTimeMaintainer;
import com.example.demo.dao.JdbcDao;
import com.example.demo.profiler.JdbcProfiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.annotation.Bean;

@EnableAutoConfiguration
@EnableConfigurationProperties
@SpringBootApplication
public class DatabaseApplication {
	@Qualifier("jdbcObj")
	@Autowired
	JdbcDao jdbcDao;

	@Autowired
	DataBaseTimeMaintainer dataBaseTimeMaintainer;
	public static void main(String[] args) {
		SpringApplication.run(DatabaseApplication.class, args);

	}

	@Bean("jdbcDao")
	public JdbcDao createJdbcDao(){
		return (JdbcDao) Enhancer.create(JdbcDao.class, new JdbcProfiler(jdbcDao,dataBaseTimeMaintainer));

	}
}
