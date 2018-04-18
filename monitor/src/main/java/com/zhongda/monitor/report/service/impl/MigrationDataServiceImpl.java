package com.zhongda.monitor.report.service.impl;

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
import com.zhongda.monitor.report.mapper.MigrationDataMapper;
import com.zhongda.monitor.report.model.ReportConfig;
import com.zhongda.monitor.report.model.ReportData;
import com.zhongda.monitor.report.service.MigrationDataService;
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
	/**
	 * 查询报告数据  "2018-04-10 00:00:00", "2018-04-10 00:10:00"
	 */
	@Override
	public Map<String,List<ReportData>> selectRepotrData(String beginTime,String endTime) {
		
		Map<String,List<ReportData>> map  = new HashMap<String, List<ReportData>>();
		
		//这个对像是在服务器启动的时候生成的一个report_config表对象
		List<ReportConfig> reportConfig = ReportConfigOpUtils.reportConfigs;
		
		for(ReportConfig c: reportConfig){
						
			int pojoId  = c.getProject_id();
			//查询pojoId下的原始数据表名
			List<StatisticChart> tables = statisticChartService.selectByPojoId(pojoId);
			
						
			//查询当前pojoId的数据
			for(StatisticChart V: tables){
				
				List<ReportData> datas =  migrationData.selectRepotrData( V.getTableName(),beginTime,endTime,String.valueOf(V.getProjectId()));
				
				map.put(V.getTableName(), datas);
				
				logger.info(pojoId+"项目下的"+V.getTableName()+"表,查询"+beginTime+"——"+endTime+"的数据");
			}
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
			
			if(!Maps.get(key).isEmpty()){
				
				migrationData.insertData(Maps.get(key));	
				logger.info("归档了"+beginTime+"——"+endTime+"的全部数据");
			}else{
				logger.error("归档beginTime:"+beginTime+"到endTime:"+endTime+"失败"+Maps.toString());
			
			}
			
			
		}
		
		logger.info("==============================================归档beginTime:"+beginTime+"到endTime:"+endTime+"=====================================================");
		
	}

}
