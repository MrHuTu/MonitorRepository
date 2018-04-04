package com.zhongda.monitor.business.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zhongda.monitor.business.model.Project;
import com.zhongda.monitor.business.service.ProjectService;
import com.zhongda.monitor.business.service.WordUtil2007Service;
import com.zhongda.monitor.business.utils.WordUtil2007;
/**
 * 生成报告服务类
 * @author huchao
 * 2018年4月2日17:23:20
 *
 */
@Service
@Scope(value="prototype")
public class WordUtil2007ServiceImpl implements WordUtil2007Service {
	private static final Logger logger = Logger.getLogger(WordUtil2007ServiceImpl.class);
	
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
		
		Map<Object,Object> map = analysis(pojoId);
		
		String name = (String) map.get("name");
		
		XWPFDocument doc = (XWPFDocument) map.get("doc");
		
		//解析之后的word文件存放的临时路径
		fileName = download +name+".docx";		
		
		try {
			FileOutputStream fopts = new FileOutputStream(fileName);
						
			doc.write(fopts);
			
			logger.info("word报告解析成功:"+fileName);
			
			fopts.close();
			
			doc.close();
			
		} catch (IOException e) {
			
			logger.error("word报告解析失败:"+fileName);
		}
		
		return fileName;

		
	}
	/**
	 * 解析测试用模板,替换占位符
	 * @param pojoId
	 * @return Map 文件名，XWPFDocument对象
	 */
	public  Map<Object,Object> analysis(String pojoId){
		
		 Map<Object,Object> map = new HashMap<Object, Object>();
		 
		Map<String, Object> param = new HashMap<String, Object>();
		
		Project pj = projectService.selectByPrimaryKey(pojoId);	
		
		String name = pj.getProjectName();
		
		param.put("${name}", name+"日报");	
		
		XWPFDocument doc = WordUtil2007.generateWord(param, templatePath);
		
		map.put("doc",doc );
		
		map.put("name", name+"日报");
		
		return map;
	}

}
