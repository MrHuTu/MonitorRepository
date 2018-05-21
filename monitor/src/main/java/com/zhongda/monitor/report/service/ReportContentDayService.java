package com.zhongda.monitor.report.service;

import java.util.List;

import com.zhongda.monitor.report.model.ReportContentDay;

public interface ReportContentDayService {
	
	public List<ReportContentDay>  selectDayConfigById(String id);

}
