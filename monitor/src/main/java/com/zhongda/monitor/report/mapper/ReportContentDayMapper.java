package com.zhongda.monitor.report.mapper;

import java.util.List;

import com.zhongda.monitor.report.model.ReportContentDay;
/**
 * 日报配置
 */

public interface ReportContentDayMapper {
		
	public List<ReportContentDay>  selectDayConfigById(String id);
	
}
