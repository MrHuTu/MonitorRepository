package com.zhongda.monitor.report.service;

import java.util.HashMap;
import java.util.List;

import com.zhongda.monitor.report.model.ReportData;


public interface MigrationDataService {
	
	public HashMap<String, List<ReportData>> selectRepotrData(String beginTime,String endTime);
	
	
	public  void insertData(String beginTime,String endTime);
}
