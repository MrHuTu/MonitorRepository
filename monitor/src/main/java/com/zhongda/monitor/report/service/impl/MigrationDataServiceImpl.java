package com.zhongda.monitor.report.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhongda.monitor.business.model.StatisticChart;
import com.zhongda.monitor.business.service.StatisticChartService;
import com.zhongda.monitor.core.utils.CacheUtils;
import com.zhongda.monitor.report.mapper.MigrationDataMapper;
import com.zhongda.monitor.report.model.ReportConfig;
import com.zhongda.monitor.report.model.ReportData;
import com.zhongda.monitor.report.service.MigrationDataService;
import com.zhongda.monitor.report.service.ReportDataService;
import com.zhongda.monitor.report.utils.ReportConfigOpUtils;
/**
 * 归档report_config 配置了项目的数据
 * @author Administrator
 *
 */
@Service
public class MigrationDataServiceImpl implements MigrationDataService {
	private static final Logger logger = LoggerFactory.getLogger(MigrationDataServiceImpl.class);
	@Autowired
	MigrationDataMapper migrationData;
	
	@Autowired 
	StatisticChartService statisticChartService;
	
	@Autowired 
	ReportDataService reportDataService;
	
	
	/**
	 * 查询报告数据  "2018-04-10 00:00:00", "2018-04-10 00:10:00"
	 */
	@Override
	public  void selectRepotrData(String beginTime,String endTime) {
	
		Map<String,List<ReportData>> map  = new HashMap<String, List<ReportData>>();
		
		//数据迁移的起始时间，和结束时间当key
		String key =beginTime+endTime;
		
		Object obj = CacheUtils.get("reportCache", key);
		
		//这个对像是在服务器启动的时候生成的一个report_config表对象      
		List<ReportConfig> reportConfig = ReportConfigOpUtils.reportConfigs;
		
	
		if( reportConfig==null) return;
		
			for(ReportConfig c: reportConfig){
				
				//report_confi配置的项目
				int pojoId  = c.getProject_id();
				
				//查询pojoId下的原始数据表名
				List<StatisticChart> tables = statisticChartService.selectByPojoId(pojoId);											
				
				if(tables==null) return;
				
				if(obj==null){
					
					//当前归档数据的集合
					List<ReportData> datas = null;
				
					//查询当前pojoId的数据
					for(StatisticChart V: tables){
							
						 datas =  migrationData.selectRepotrData( V.getTableName(),beginTime,endTime,String.valueOf(V.getProjectId()));		
						 
						 if(datas==null) continue;
						
						 map.put(V.getTableName(), datas);
						 
						logger.info(pojoId+"项目下的"+V.getTableName()+"表,查询"+beginTime+"——"+endTime+"的数据");
					}
					
				}
				
			}
			
			if(map.size()>0) CacheUtils.put("reportCache", key, map);
		
		
		
		//return map;
		
	}
	/**
	 * 数据迁移
	 */
	@SuppressWarnings("unchecked")
	@Override
	public synchronized  void insertData(String beginTime,String endTime) {
		
		String key =beginTime+endTime;
			
		Object obj = CacheUtils.get("reportCache", key);
		
		boolean insert = true;
		if(obj==null){			
				selectRepotrData(beginTime, endTime);							
		}
		
		Map<String,List<ReportData>>  ReportDatas= (Map<String, List<ReportData>>)CacheUtils.get("reportCache", key);
		if(ReportDatas==null) return;				
		Iterator<String> ite = ReportDatas.keySet().iterator();
			
			while(ite.hasNext()){
				
				String  mapKey= ite.next();		
				
				if(ReportDatas.get(mapKey).size()>0){
					
					List<ReportData> lists = ReportDatas.get(mapKey);
					
					Iterator<ReportData>  ite1= lists.iterator();
					
					while(ite1.hasNext()){
						
						ReportData reportData = ite1.next();
											
						String sensor_number = reportData.getSensor_number();
						
						String smu_channel = reportData.getSmu_channel();
						
						String smu_number =  reportData.getSmu_number();						
						
						String current_times = new DateTime(reportData.getCurrent_times()).toString("YYYY-MM-dd HH:mm:ss");
						
						if(!reportDataService.selectReportData(sensor_number, smu_channel, smu_number, current_times)){//数据不存在该数据库进入if
							
							insert = true;//允许归档数据
							
						}else{
							insert = false;//数据库已经存在该数据，无需归档
						
						};
					}	
					if(insert){
						migrationData.insertData(ReportDatas.get(mapKey));	
						logger.info("==============================================归档beginTime:"+beginTime+"到endTime:"+endTime+"=====================================================");
					}
				}												
			}
					
		
		
	}

}
