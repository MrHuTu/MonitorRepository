package com.zhongda.monitor.report.service;

import org.springframework.web.multipart.MultipartFile;

import com.zhongda.monitor.core.model.Result;

public interface ReportPicService{


	public Result<String> insertPic(MultipartFile[] file,String projectId);
}
