package com.zhongda.monitor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.zhongda.monitor.report.utils.SpringContextUtil;

@SpringBootApplication
@MapperScan("com.zhongda.monitor.*.mapper")
@EnableScheduling  
public class MonitorApplication {

	public static void main(String[] args) {
		ApplicationContext   monitorApplication =SpringApplication.run(MonitorApplication.class, args);
		 SpringContextUtil.setApplicationContext(monitorApplication);  
	}
}
