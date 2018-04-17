package com.zhongda.monitor.report.task;

import org.joda.time.DateTime;
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
	
	@Autowired
	  MigrationDataService migrationDataService;
	  
	
	/**
	 * 
	 * 定时归档
	 * 
	 * @throws Exception  cron="0 0 12 * * ?" 每天中午12点触发 
	 */
	@Scheduled(cron = "0 0 00 * * ?")
	public void archive() throws Exception {	//"2018-04-10 00:00:00", "2018-04-10 00:10:00"		
		DateTime start = DateTime.now();
		String beginTime = start.toString("YYYY-MM-DD HH:mm:ss");
		String endTime = start.plusMinutes(10).toString("YYYY-MM-DD HH:mm:ss");		
		migrationDataService.insertData(beginTime,endTime);
	}	
	@Scheduled( cron="0 0 08 * * ?")
	public void archive1() throws Exception {	
		DateTime start = DateTime.now();
		String beginTime = start.toString("YYYY-MM-DD HH:mm:ss");
		String endTime = start.plusMinutes(10).toString("YYYY-MM-DD HH:mm:ss");
		migrationDataService.insertData(beginTime,endTime);
	}	
	@Scheduled( cron="0 0 16 * * ?")
	public void archive2() throws Exception {
		DateTime start = DateTime.now();
		String beginTime = start.toString("YYYY-MM-DD HH:mm:ss");
		String endTime = start.plusMinutes(10).toString("YYYY-MM-DD HH:mm:ss");
		
		migrationDataService.insertData(beginTime,endTime);
	}
	
	
}
