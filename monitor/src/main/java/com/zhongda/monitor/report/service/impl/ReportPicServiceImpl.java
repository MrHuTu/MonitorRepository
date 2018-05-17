package com.zhongda.monitor.report.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zhongda.monitor.core.model.Result;
import com.zhongda.monitor.report.mapper.ReportPicMapper;
import com.zhongda.monitor.report.model.ReportPic;
import com.zhongda.monitor.report.service.ReportPicService;
import com.zhongda.monitor.report.utils.GitYmlParaUtils;
/**
 * 批量上传文件
 * @author Administrator
 *
 */
@Service
public class ReportPicServiceImpl implements ReportPicService {
	@Autowired
	GitYmlParaUtils gitYmlParaUtils;
	@Autowired
	ReportPicMapper reportPicMapper;

	@Override
	public Result<String> insertPic(MultipartFile[] file) {
		
		boolean success = false;
		
		
		
		try {
			for(MultipartFile v: file){
				
			
			
			String fileName = v.getOriginalFilename();		
			
			if(fileName.indexOf(".png")==0 ||fileName.indexOf(".gif")==0 ){
				
				
				return new Result<String>().setCode(Result.FAILURE).setMsg("上传类型只支持 .png,.gif格式图片");
				
			}
			
			String path  = gitYmlParaUtils.accordingOsGetParm("upload")+fileName;
			
			byte[] by = v.getBytes();
			
			FileOutputStream out  = new FileOutputStream(path);
			
			out.write(by);
			
			out.close();
			
			ReportPic reportPic = new ReportPic();
			
			reportPic.setPath(path);
			
			success  =  reportPicMapper.insertPic(reportPic);
			
			if(!success) break;
			
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(success){
			
			return  new Result<String>().setCode(Result.SUCCESS).setMsg("上传成功");
		}else{
			return  new Result<String>().setCode(Result.FAILURE).setMsg("上传失败");
		}
		
		
		
		
		
		
		
		
	}

}
