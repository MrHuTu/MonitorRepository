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
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zhongda.monitor.business.model.Project;
import com.zhongda.monitor.business.service.ProjectService;
import com.zhongda.monitor.report.configclass.configmodel.CreateTableConfig;
import com.zhongda.monitor.report.model.fictitious.ErrorCode;
import com.zhongda.monitor.report.model.fictitious.ProjectPara;
import com.zhongda.monitor.report.service.ProjectParaService;
import com.zhongda.monitor.report.service.WordUtil2007Service;
import com.zhongda.monitor.report.utils.CopyFileUtils;
import com.zhongda.monitor.report.utils.ReportConfigOpUtils;
import com.zhongda.monitor.report.utils.SpringContextUtil;
import com.zhongda.monitor.report.utils.Wordl2007Utis;


/**
 * 生成报告总入口服务类 ，看这里服务类可以基本明白怎么取开发一个word报告模板
 * @author huchao
 * 2018年4月2日17:23:20
 * 在调用generateWord时由于word文档的模板以后都会固定统一，所以文本替换直接写死了。
 * 不同项目类型的表格he数据不一样，这里用到了反射和数据库表的配置
 */
@Service
@Scope(value="prototype")
public class WordUtil2007ServiceImpl implements WordUtil2007Service {
	
	private static final Logger logger = LoggerFactory.getLogger(WordUtil2007ServiceImpl.class);
	
	@Value("${modelpath}")
	private String modelpath;
	
	@Value("${tempmodel}")
	private String tempmodel;
	
	@Value("${downrepor}")
	private String downreport;
	
	@Autowired
	ProjectService projectService;
	@Autowired
	ProjectParaService projectParaService;
	
	
	/**
	 * return String 返回报告文件路径
	 */
	@SuppressWarnings("unchecked")
	@Override
	public synchronized String generateWord(String pojoId,String time) {
		
		
		
		//见模板复制到零时目录，防止模板文件被修改
		CopyFileUtils.copyFile(modelpath, tempmodel);
			
		DateTime dateTime  = new DateTime(time);
		
		if(!dateTime.isBeforeNow()){//如果参数time超过当前日期，返回错误提示信息
			
			return ErrorCode.ERROR4;
		}
	/*	
		if( dateTime1.getHourOfDay()<17){//当前时间小于17点，返回错误提示信息
			
			return ErrorCode.ERROR5;
			
		}*/
				
		String fileName= null;
		
		Object obj = analysis(pojoId,time);//核心处理
		
		String name = null;
		
		XWPFDocument doc = null;
		
		if(obj instanceof Map){
			
			Map<Object,Object> map1 = 	(Map<Object,Object>)obj;
			
			 name = (String) map1.get("name");
			
			 doc = (XWPFDocument) map1.get("doc");
			 
			//解析之后的word文件存放的临时路径
			fileName = downreport+name+".docx";		
				
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
	 * @return Map 文件名，XWPFDocument对象。String 错误提示信息
	 */
	public  Object analysis(String pojoId,String time){
		
		//这个map 存放模板文档实例，和非表格占位符		
		 Map<Object,Object> map = new HashMap<Object, Object>();
		 
		Map<String, Object> param = new HashMap<String, Object>();
		
		//用来判断该项目开关是否开启
		if(!ReportConfigOpUtils.verifyreportConfig(pojoId)){
			
			return ErrorCode.ERROR1;
			
		}else{
			//这个时在服务器启动时就加载了的数据，用来验证该项目下的监测参数是否支持生成报告
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
			
			//替换文本占位符,将来文本内容可做配置化，这一部分需要修改，以为最终模板还没确定下来
			Project pj = projectService.selectByPrimaryKey(pojoId);	
			
			String name = pj.getProjectName();
			
			param.put("${name}", name+"日报");
			
			//解析模板，doc可以看做一个word解析之后的xml对象
			XWPFDocument doc = Wordl2007Utis.generateWord(param, tempmodel);		
			
		
			/**根据自定义的表格样式,和自定义的表格数据处理类在doc中插入对应的表格样式，和数据
			 * ReportConfigOpUtils.gitClassPath(pojoId),这个的在服务启动时已经加载项目的配置信息,他返回一个处理表格数据bean ID.
			 * 这个bean 就是自定义的表格数据处理类,这个处理类要实现一个接口（因为callMethod这个方法已经把掉用者的方法名称写死），在这个bean中会根据
			 * ReportConfig中的配置信息去生成表格。ReportConfig中的配置信息就是你写的自定义的表格样式,定义的这个样式类也要实现一个接口，愿意和上面一样，在wordUtil2007。Utils
			 * 中也用到了反射，我把被调用的方法写死了，方法名称定义在一个接口
			 * 有些地方设计还有瑕疵，有看到和胡超提一下
			 * 
			 * 到目前为止 开发一个word文档报告，要做的事件在这里全部可以体现。
			 * 第一，配置项目开关
			 * 第二。配置可用参数
			 * 第三，写好表格样式类实现BastTableClass,建议写到tableclass包下。和处理数据的类实现SedimentationFill接口,建有写到filldata.impl包下
			 * 第四.将第3步的处理数据的类的beanid配置到数据库 。和第一部时同一个表
			 * 
			 * 
			 * 
			 */
			callMethod(ReportConfigOpUtils.gitClassPath(pojoId),doc,pojoId,time);
			
			map.put("doc",doc );
			
			map.put("name", name+"日报");
			
			return map;
			
		}
		
	}
	 /**
	 * 呼叫方法  格式表格数据
	 * @param method
	 * @param value
	 * @return 
	 */
	
	private static CreateTableConfig callMethod(String className,XWPFDocument doc2,String pojoId,String time){
		
		 String methodName =  "fillData";
		  
				 
		  CreateTableConfig  result = null;
		  
		try {
			
			Object obj = SpringContextUtil.getBean(className);
			
			  //获取方法  			
			 Method m = obj.getClass().getMethod(methodName,XWPFDocument.class,String.class,String.class);
			 
			  //调用方法  
			 result =  (CreateTableConfig) m.invoke(obj, doc2,pojoId,time);
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
