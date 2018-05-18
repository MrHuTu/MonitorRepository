package com.zhongda.monitor.report.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zhongda.monitor.core.model.Result;
import com.zhongda.monitor.report.service.ReportPicService;

/**
 * 
 * @author 胡超
 * 处理文件上传
 * 2018年5月16日16:25:21
 *
 */
@RestController()
@RequestMapping(value = "/uploadfiles")
@Api(value = "图片上传（支持多图上传，sahift选中图片）", tags = { "图片上传" })
public class UploadFilesContorller {
	@Autowired
	ReportPicService reportPicService;
	
	@PostMapping("/pic")
	@ApiOperation(value = "图片上传 --chao.hu", notes = "文件上传",  httpMethod = "POST")
	private Result<String> generateWord(@RequestParam("file")MultipartFile[] file,@RequestParam("projectId") String projectId) throws IOException {
		
	
		return reportPicService.insertPic(file,projectId);							

	}
	
}
