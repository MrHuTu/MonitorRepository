package com.zhongda.monitor.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhongda.monitor.report.mapper.ReportContentDayMapper;
import com.zhongda.monitor.report.model.ReportContentDay;
import com.zhongda.monitor.report.service.ReportContentDayService;
/**
 *处理日报表的方法
 * @author 胡超
 * 
 *
 */
@Service("ReportContentDayServiceImpl")
public class ReportContentDayServiceImpl implements ReportContentDayService {
	
	@Autowired
	ReportContentDayMapper reportContentDayMapper;

	@Override
	public List<ReportContentDay> selectDayConfigById(String id) {
	
		return reportContentDayMapper.selectDayConfigById(id);
	}
	

}
