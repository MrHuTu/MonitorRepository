package com.zhongda.monitor.report.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.zhongda.monitor.core.model.Result;
import com.zhongda.monitor.report.model.ReportContentDay;

public interface ReportContentDayService {
	
	public List<ReportContentDay>  selectDayConfigById(String id);

}
