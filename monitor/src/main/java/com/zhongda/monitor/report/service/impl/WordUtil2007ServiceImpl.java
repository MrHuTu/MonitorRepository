package com.zhongda.monitor.report.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlCursor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zhongda.monitor.business.model.Project;
import com.zhongda.monitor.business.service.ProjectService;
import com.zhongda.monitor.report.configclass.configmodel.CreateTableConfig;
import com.zhongda.monitor.report.model.fictitious.ErrorCode;
import com.zhongda.monitor.report.model.fictitious.ProjectPara;
import com.zhongda.monitor.report.model.fictitious.SideTableData;
import com.zhongda.monitor.report.service.ProjectParaService;
import com.zhongda.monitor.report.service.WordUtil2007Service;
import com.zhongda.monitor.report.utils.ReportConfigOpUtils;
import com.zhongda.monitor.report.utils.SpringContextUtil;
import com.zhongda.monitor.report.utils.Wordl2007Utis;


/**
 * 生成报告总入口服务类
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
	@Autowired
	ProjectParaService projectParaService;
	
	
	/**
	 * return String 返回报告文件路径
	 */
	@SuppressWarnings("unchecked")
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
				//不可知错误
				return ErrorCode.ERROR3;
			}
			
		}
		
		
		
		
	

		
	}
	/**
	 * 解析测试用模板,替换占位符
	 * @param pojoId
	 * @return Map 文件名，XWPFDocument对象
	 */
	public  Object analysis(String pojoId){
		//这个map 存放模板文档实例，和非表格占位符
		 Map<Object,Object> map = new HashMap<Object, Object>();
		 
		Map<String, Object> param = new HashMap<String, Object>();
		
		if(!ReportConfigOpUtils.verifyreportConfig(pojoId)){
			
			return ErrorCode.ERROR1;
			
		}else{
			List<ProjectPara> ProjectParas = ReportConfigOpUtils.projectPara;
						
			Iterator<ProjectPara>  ite = ProjectParas.iterator();
			
			while(ite.hasNext()){
				
				ProjectPara projectPara = ite.next();
				
				if(pojoId.equals(String.valueOf(projectPara.getProject_id()))){
					
					if(!ReportConfigOpUtils.verifyReportPara(String.valueOf(projectPara.getMonitor_type()))){
						
						return ErrorCode.ERROR2;
						
					};
					
				};
				
			};
			
			Project pj = projectService.selectByPrimaryKey(pojoId);	
			
			String name = pj.getProjectName();
			
			param.put("${name}", name+"日报");	
			
			XWPFDocument doc = Wordl2007Utis.generateWord(param, templatePath);
			
			map.put("doc",doc );
			
			map.put("name", name+"日报");
			
			
			callMethod(ReportConfigOpUtils.gitClassPath(pojoId),doc,pojoId);
			return map;
			
		}
		
	}
	 /**
	 * 呼叫方法  格式表格数据
	 * @param method
	 * @param value
	 * @return 
	 */
	
	private static CreateTableConfig callMethod(String className,XWPFDocument doc2,String pojoId){
		ApplicationContextAware applicationContext;
		 String methodName =  "fillData";
		  
				 
		  CreateTableConfig  result = null;
		try {
			String[]  c= className.split("\\.");
		
			
			Object obj = SpringContextUtil.getBean(c[c.length-1]);
			
			//Class clz = Class.forName(className);
			
			 //取实例  
			//Object obj = clz.newInstance();		
			
			  //获取方法  			
			 Method m = obj.getClass().getMethod(methodName,XWPFDocument.class,String.class);
			 
			  //调用方法  
			 result =  (CreateTableConfig) m.invoke(obj, doc2,pojoId);
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			
			e.printStackTrace();
		} catch (SecurityException e) {
			
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			
			e.printStackTrace();
		}
		return result;
		 
	 }
}
