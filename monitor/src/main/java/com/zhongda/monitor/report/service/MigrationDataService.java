package com.zhongda.monitor.report.service;


public interface MigrationDataService {
	
	public void selectRepotrData(String beginTime,String endTime);
	
	
	public  void insertData(String beginTime,String endTime);
}
