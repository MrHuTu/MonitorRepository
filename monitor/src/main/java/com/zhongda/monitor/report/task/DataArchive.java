package com.zhongda.monitor.report.task;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import com.zhongda.monitor.report.service.MigrationDataService;
import com.zhongda.monitor.report.utils.GitYmlParaUtils;

/**
 * 
 * @author huchao 2018年4月13日15:38:39
 * 数据归档
 */
@Configuration
public class DataArchive {
	
	//private static final Logger logger = LoggerFactory.getLogger(DataArchive.class);
	
	@Autowired
	 MigrationDataService migrationDataService;
	
	
	@Autowired
	GitYmlParaUtils gitYmlParaUtils;
	
	  
	
	/**
	 * 
	 * 定时归档
	 * 
	 * @throws Exception  cron="0 0 12 * * ?" 每天中午12点触发 
	 */
	@Scheduled( fixedRate = 1000*60*30)
	public void archive() throws Exception {	//"2018-04-10 00:00:00", "2018-04-10 00:10:00"		

		Map<String,String>  map= gitYmlParaUtils.getSetTime(0);
		
		migrationDataService.insertData(map.get("start"),map.get("end"));
		
		Map<String,String>  map1= gitYmlParaUtils.getSetTime(1);
		
		migrationDataService.insertData(map1.get("start"),map1.get("end"));
		
		Map<String,String>  map2= gitYmlParaUtils.getSetTime(2);
		
		migrationDataService.insertData(map2.get("start"),map2.get("end"));
	}	
	
	/*@Scheduled( fixedRate =1000*60*20)
	public void archive1() throws Exception {	
		
		
	}	
	
	@Scheduled( fixedRate = 1000*60*20)
	public void archive2() throws Exception {
	
		
		
	}*/
	
	
	
}
