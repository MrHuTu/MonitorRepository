package com.zhongda.monitor.report.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zhongda.monitor.report.model.fictitious.ErrorCode;
import com.zhongda.monitor.business.model.Project;
import com.zhongda.monitor.business.service.ProjectService;
import com.zhongda.monitor.report.service.WordUtil2007Service;
import com.zhongda.monitor.report.utils.ReportConfigOp;
import com.zhongda.monitor.report.utils.WordUtil2007;


/**
 * 生成报告服务类
 * @author huchao
 * 2018年4月2日17:23:20
 *
 */
@Service
@Scope(value="prototype")
public class WordUtil2007ServiceImpl implements WordUtil2007Service {
	
	private static final Logger logger = LoggerFactory.getLogger(WordUtil2007ServiceImpl.class);
	
	@Value("${templatepath}")
	private String templatePath;
	
	@Value("${download}")
	private String download;
	
	
	@Autowired
	ProjectService projectService;
	/**
	 * return String 返回报告文件路径
	 */
	@Override
	public String generateWord(String pojoId) {
		
		String fileName= null;
		
		Object obj = analysis(pojoId);
		String name = null;
		XWPFDocument doc = null;
		if(obj instanceof Map){
			Map<Object,Object> map1 = 	(Map<Object,Object>)obj;
			
			 name = (String) map1.get("name");
			
			 doc = (XWPFDocument) map1.get("doc");
			 
			//解析之后的word文件存放的临时路径
				fileName = download +name+".docx";		
				
				try {
					
					FileOutputStream fopts = new FileOutputStream(fileName);
								
					doc.write(fopts);
					
					logger.info("word报告解析成功:"+fileName);
								
					doc.close();
					
					fopts.close();
					
				} catch (IOException e) {
					//当doc 写入fopts中时,会尝试修改模板内容,报告模板会设置成只读,这样就会抛出文件拒绝访问异常异常，这个异常不影响程序的正常运行，在当前位置将改异常吞并
					//logger.error("word报告解析失败:"+e);
				}
				return fileName;
				
		}else{
			if(obj instanceof String ){
				return (String)obj;
			}else{
				return ErrorCode.ERROR3;
			}
			
		}
		
		
		
		
	

		
	}
	/**
	 * 解析测试用模板,替换占位符
	 * @param pojoId
	 * @return Map 文件名，XWPFDocument对象
	 */
	public  /*Map<Object,Object>*/Object analysis(String pojoId){
		
		 Map<Object,Object> map = new HashMap<Object, Object>();
		 
		Map<String, Object> param = new HashMap<String, Object>();
		ReportConfigOp reportConfigOp = new ReportConfigOp();
		if(!reportConfigOp.verifyreportConfig(pojoId)){
			return ErrorCode.ERROR1;
		}else{
			
			Project pj = projectService.selectByPrimaryKey(pojoId);	
			
			String name = pj.getProjectName();
			
			param.put("${name}", name+"日报");	
			
			XWPFDocument doc = WordUtil2007.generateWord(param, templatePath);
			
			map.put("doc",doc );
			
			map.put("name", name+"日报");
			
			return map;
		}
		
	}
	
}
