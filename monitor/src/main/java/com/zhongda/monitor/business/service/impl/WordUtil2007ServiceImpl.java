package com.zhongda.monitor.business.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class WordUtil2007ServiceImpl implements WordUtil2007Service {
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
		
		Map<Object,Object> map= analysis(pojoId);
		XWPFDocument doc = (XWPFDocument) map.get("doc");
		String name = (String) map.get("name");
		FileOutputStream fopts;
		File file = new File(download);
		String path = download+name+"日报"+".docx";
		if(!file.exists()){
			file.mkdir();
		}
		try {
			fopts = new FileOutputStream(path);
			doc.write(fopts);
			fopts.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;

		
	}
	/**
	 * 解析测试用模板
	 * @param pojoId
	 * @return
	 */
	public  Map<Object,Object> analysis(String pojoId){
		 Map<Object,Object> map = new HashMap<Object, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		Project pj = projectService.selectByPrimaryKey(pojoId);		
		String name = pj.getProjectName();
		param.put("${name}", name+"日报");		
		XWPFDocument doc = WordUtil2007.generateWord(param, templatePath);
		
		map.put("doc",doc );
		map.put("name", name);
		return map;
	}

}
