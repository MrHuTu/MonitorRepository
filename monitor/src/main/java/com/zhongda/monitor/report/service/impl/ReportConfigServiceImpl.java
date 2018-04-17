package com.zhongda.monitor.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhongda.monitor.report.mapper.ReportConfigMapper;
import com.zhongda.monitor.report.model.ReportConfig;
import com.zhongda.monitor.report.service.ReportConfigService;
@Service
public class ReportConfigServiceImpl implements ReportConfigService {
	@Autowired
	ReportConfigMapper reportConfigMapper;
	@Override
	public List<ReportConfig> selectReportConfig() {
		
		return reportConfigMapper.selectReportConfig();
		
	}

}
