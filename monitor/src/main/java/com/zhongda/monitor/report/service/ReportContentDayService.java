package com.zhongda.monitor.report.service;

import org.springframework.web.multipart.MultipartFile;

import com.zhongda.monitor.core.model.Result;

public interface ReportContentDayService {
	
	public  Result<String> addPic(MultipartFile file);
	
	
	public  String selectDayConfigById();

}
