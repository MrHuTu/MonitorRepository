package com.zhongda.monitor.report.service;


public interface ReportDataService {
	
	public boolean selectReportData(String sensor_number,String smu_channel,String smu_number,String current_times);
}
