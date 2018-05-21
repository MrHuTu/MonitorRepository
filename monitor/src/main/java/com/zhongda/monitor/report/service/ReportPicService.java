package com.zhongda.monitor.report.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.zhongda.monitor.core.model.Result;
import com.zhongda.monitor.report.model.ReportPic;

public interface ReportPicService{


	public Result<String> insertPic(MultipartFile[] file,String projectId);
	
	public Result<List<String>> selectPicById(String id);
	
	public List<ReportPic> selectPic();
}
