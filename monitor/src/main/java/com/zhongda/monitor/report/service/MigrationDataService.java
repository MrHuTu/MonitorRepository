package com.zhongda.monitor.report.service;

import java.util.List;
import java.util.Map;

import com.zhongda.monitor.report.model.ReportData;

public interface MigrationDataService {
	
	public Map<String,List<ReportData>> selectRepotrData(String beginTime,String endTime);
	
	
	public  void insertData(String beginTime,String endTime);
}
