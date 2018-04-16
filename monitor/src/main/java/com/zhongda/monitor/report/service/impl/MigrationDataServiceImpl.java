package com.zhongda.monitor.report.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhongda.monitor.business.model.StatisticChart;
import com.zhongda.monitor.business.service.StatisticChartService;
import com.zhongda.monitor.business.utils.WeatherUtils;
import com.zhongda.monitor.report.mapper.MigrationData;
import com.zhongda.monitor.report.model.ReportData;
import com.zhongda.monitor.report.service.MigrationDataService;
@Service
public class MigrationDataServiceImpl implements MigrationDataService {
	private static final Logger logger = LoggerFactory.getLogger(MigrationDataServiceImpl.class);
	@Autowired
	MigrationData migrationData;
	
	@Autowired 
	StatisticChartService statisticChartService;
	/**
	 * 查询报告数据  "2018-04-10 00:00:00", "2018-04-10 00:10:00"
	 */
	@Override
	public Map<String,List<ReportData>> selectRepotrData(String beginTime,String endTime) {
		Map<String,List<ReportData>> map  = new HashMap<String, List<ReportData>>();
		List<String> tableName = new ArrayList<String>();		
		
		List<StatisticChart> tables = statisticChartService.selectByPojoId(261);
		
		for(StatisticChart v: tables){
			tableName.add(v.getTableName());
			
		}
		
		for(String V: tableName){
			
			List<ReportData> datas =  migrationData.selectRepotrData( V,beginTime,endTime);
			
			map.put(V, datas);
		}
		
		return map;
		
	}
	/**
	 * 数据迁移
	 */
	@Override
	public void insertData(String beginTime,String endTime) {
		
		Map<String, List<ReportData>> Maps = selectRepotrData(beginTime, endTime);
		
		Iterator<String> ite = Maps.keySet().iterator();
		
		while(ite.hasNext()){
			
			String key  = ite.next();
			
			migrationData.insertData(Maps.get(key));	
			logger.info("归档beginTime到endTime的数据");
		}
		
	}

}
