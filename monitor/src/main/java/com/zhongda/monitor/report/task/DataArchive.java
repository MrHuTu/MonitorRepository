package com.zhongda.monitor.report.task;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zhongda.monitor.report.service.MigrationDataService;

/**
 * 
 * @author huchao 2018年4月13日15:38:39
 * 数据归档
 */
@Component
public class DataArchive {
	
	private static final Logger logger = LoggerFactory.getLogger(DataArchive.class);
	
	@Autowired
	 MigrationDataService migrationDataService;
	  
	
	/**
	 * 
	 * 定时归档
	 * 
	 * @throws Exception  cron="0 0 12 * * ?" 每天中午12点触发 
	 */
	@Scheduled( cron="0 0 00 * * ?")
	public void archive() throws Exception {	//"2018-04-10 00:00:00", "2018-04-10 00:10:00"		
		
		DateTime start = DateTime.now();
		
		String beginTime = start.toString("YYYY-MM-dd HH:mm:ss");
		
		logger.info("现在是:"+beginTime+",准备数据归档");
		
		String endTime = start.plusMinutes(10).toString("YYYY-MM-dd HH:mm:ss");	
		
		migrationDataService.insertData(beginTime,endTime);
	}	
	
	@Scheduled( cron="0 0 08 * * ?")
	public void archive1() throws Exception {	
		
		DateTime start = DateTime.now();
		
		String beginTime = start.toString("YYYY-MM-dd HH:mm:ss");
		
		logger.info("现在是:"+beginTime+",准备数据归档");
		
		String endTime = start.plusMinutes(10).toString("YYYY-MM-dd HH:mm:ss");
		
		migrationDataService.insertData(beginTime,endTime);
	}	
	
	@Scheduled( cron="0 0 16 * * ?")
	public void archive2() throws Exception {
		
		DateTime start = DateTime.now();
		
		String beginTime = start.toString("YYYY-MM-dd HH:mm:ss");
		
		logger.info("现在是:"+beginTime+",准备数据归档");
		
		String endTime = start.plusMinutes(10).toString("YYYY-MM-dd HH:mm:ss");
		
		migrationDataService.insertData(beginTime,endTime);
	}
	
	
}
