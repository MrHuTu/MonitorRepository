package com.zhongda.monitor.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhongda.monitor.report.mapper.ReportParaMapper;
import com.zhongda.monitor.report.model.ReportPara;
import com.zhongda.monitor.report.service.ReportParaService;
@Service
public class ReportParaServiceImpl implements ReportParaService {
	@Autowired
	ReportParaMapper reportParaMapper;
	@Override
	public List<ReportPara> selectReportPara() {
		
		return reportParaMapper.selectReportPara();
	}

}
