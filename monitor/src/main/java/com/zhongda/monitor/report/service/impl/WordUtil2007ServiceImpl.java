package com.zhongda.monitor.report.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.zhongda.monitor.business.service.ProjectService;
import com.zhongda.monitor.report.configclass.filldata.FillBasics;
import com.zhongda.monitor.report.exception.ImplErrorException;
import com.zhongda.monitor.report.model.fictitious.ErrorCode;
import com.zhongda.monitor.report.service.ProjectParaService;
import com.zhongda.monitor.report.service.WordUtil2007Service;
import com.zhongda.monitor.report.utils.CopyFileUtils;
import com.zhongda.monitor.report.utils.FillWordMapUtils;
import com.zhongda.monitor.report.utils.GitYmlParaUtils;
import com.zhongda.monitor.report.utils.ReportConfigOpUtils;
import com.zhongda.monitor.report.utils.SpringContextUtil;
import com.zhongda.monitor.report.utils.VerificationReport;
import com.zhongda.monitor.report.utils.Wordl2007Utis;


/**
 * 生成报告总入口服务类 ，看这里服务类可以基本明白怎么取开发一个word报告模板(统一的入口方法，不建议在这里做具体模板的业务逻辑修改
 * )
 * @author huchao
 * 2018年4月2日17:23:20
 * 在调用generateWord时由于word文档的模板以后都会固定统一，所以文本替换直接写死了。
 * 不同项目类型的表格he数据不一样，这里用到了反射和数据库表的配置
 * 
 *  要增加一个参数，来表示报告文档的模式,为了暂时不影响前台的调用，这里将模式写死，接口调用方式不变
	 * D -日报
	 * W -周报
	 * M -月报
	 * Q -季报
	 * Y -年报
 */
@Service
@Scope(value="prototype")
public class WordUtil2007ServiceImpl implements WordUtil2007Service {
	
	private  Logger logger = LoggerFactory.getLogger(WordUtil2007ServiceImpl.class);
	
	private static final  String TYPD="D";
	
	private static final  String TYPW="W";
	
	private static final  String TYPM="M";
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	ProjectParaService projectParaService;
	
	@Autowired
	GitYmlParaUtils  gitYmlParaUtils;
	
	/**
	 * return String 返回报告文件路径
	 */
	@SuppressWarnings("unchecked")
	@Override
	public synchronized String generateWord(String pojoId,String time,String repotrTyp) {
		
		
		//验证当前报告生成时间
		String code = VerificationReport.whetherCreateReportTime(time);
		 
		if(code!=null) return code;
		
		
		
		//见模板复制到零时目录，防止模板文件被修改
		
		String mpdelName = ReportConfigOpUtils.getModelPath(pojoId,repotrTyp);
		
		if(mpdelName!=null){
			
			CopyFileUtils.copyFile(gitYmlParaUtils.accordingOsGetParm("path")+ReportConfigOpUtils.getModelPath(pojoId,repotrTyp),gitYmlParaUtils.accordingOsGetParm("temp")+ReportConfigOpUtils.getModelPath(pojoId,repotrTyp));
			
		}else{
			 return ErrorCode.ERROR7;
		}
		
	
			
	
				
		String fileName= null;
		
		Object obj = null;
		
			
		obj = analysis(pojoId,time,repotrTyp);//日报核心处理
						
		
		String name = null;
		
		XWPFDocument doc = null;
		
		if(obj instanceof Map){
			
			Map<Object,Object> map1 = 	(Map<Object,Object>)obj;
			
			 name = (String) map1.get("name");
			
			 doc = (XWPFDocument) map1.get("doc");
			 
			//解析之后的word文件存放的临时路径
			fileName = gitYmlParaUtils.accordingOsGetParm("temp")+name+time+".docx";		
			 
				
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
	 *  响应日报
	 * 解析测试用模板,替换占位符
	 * @param pojoId
	 * @return Map 文件名，XWPFDocument对象。String 错误提示信息
	 */
	private  Object analysis(String pojoId,String time,String repotrTyp){
		
			//这个map 存放模板文档实例，和非表格占位符		
			 Map<Object,Object> map = new HashMap<Object, Object>();
			 
			 //验证报告生成条件
			 String create = VerificationReport.whetherCreateReportCongfig(pojoId);
			 
			 if(create!=null) return create;
			 
			 Map<String, Object> 	param  = null;
										
			//获取模板填充数据 (段落类容,和固定表格的内容)
			 if(TYPD.equalsIgnoreCase(repotrTyp)){
				 
				param = FillWordMapUtils.getFillContentMapD(pojoId,time);
				 
			 }else if(TYPW.equalsIgnoreCase(repotrTyp)){
				 
					param = FillWordMapUtils.getFillContentMapW(pojoId,time);
				 
			 }
			
			
			//解析模板的路径
			 String path  =gitYmlParaUtils.accordingOsGetParm("temp")+ReportConfigOpUtils.getModelPath(pojoId,repotrTyp);
			 
			//处理文本填充,表格占位符信息填充							
			XWPFDocument doc = Wordl2007Utis.generateWord(param, path);	
																		
			 //替换页眉
			 Wordl2007Utis.replaceHeader(doc, param);
			 
			 //处理图片
			 Wordl2007Utis.insertImage(param,doc);
			
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
			//创建表格,填充数据
			 boolean succes = callMethod(ReportConfigOpUtils.gitClassPath(pojoId,repotrTyp),doc,pojoId,time, repotrTyp);
			
			if(!succes){
				
				return ErrorCode.ERROR8;
				
			}else{
				
				map.put("doc",doc );
				
				map.put("name", param.get("${name}"));
				
				return map;
				
				
			}
			
			
		
			
		//}
		
	}
	 /**
	 * 呼叫方法  格式表格数据
	 * @param method
	 * @param value
	 * @return 
	 */
	
	@SuppressWarnings("unchecked")
	private  boolean callMethod(String className,XWPFDocument doc2,String pojoId,String time,String repotrTyp){
		
		boolean  succeed = false;
		
		 String methodName =  "fillData";
		 
		 Object obj = SpringContextUtil.getBean(className);
		 
		 @SuppressWarnings("rawtypes")
		Class superclass =  FillBasics.class;
				
		if(!superclass.isAssignableFrom(obj.getClass())){					
									
			//ImplErrorException implErrorException = new 	ImplErrorException();
			
			 try {
				 new  ImplErrorException().f("className没有实现com.zhongda.monitor.report.configclass.filldata.FillBasics接口");
				 
			 }catch(ImplErrorException e){
				 
				 e.printStackTrace();
				 
			 }
										
			return succeed;
		}		
		 		  
		try {
									
			 Method m = null;
			 
			if(TYPD.equals(repotrTyp)){
				
				  m = obj.getClass().getMethod(methodName,XWPFDocument.class,String.class,String.class);
				  
				  m.invoke(obj, doc2,pojoId,time);
			}else if(TYPW.equals(repotrTyp)){
				
				  m = obj.getClass().getMethod(methodName,XWPFDocument.class,String.class);
				  
				   m.invoke(obj, doc2,pojoId);
			}
			
			succeed = true;
			 
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
		return succeed;
		 
	 }
}
