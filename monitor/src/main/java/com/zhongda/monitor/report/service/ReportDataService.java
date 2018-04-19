package com.zhongda.monitor.report.service;

import java.util.Date;
import java.util.List;

import com.zhongda.monitor.report.model.ReportData;

public interface ReportDataService {
	
	public boolean selectReportData(String sensor_number,String smu_channel,String smu_number,String current_times);
}
