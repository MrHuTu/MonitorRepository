package com.zhongda.monitor.report.utils;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import com.zhongda.monitor.business.model.Project;
import com.zhongda.monitor.business.service.ProjectService;

/**
 * 用来填充文本占位符的map集合
 * @author 胡超
 *
 */

public class FillWordMapUtils {
	
	
	
	private static HashMap<String , Object> param = new HashMap<String , Object>();

	
	public static Map<String,Object> getFillMap(String pojoId,String time){
		
		DateTime dateTime = new DateTime(time);
		
		String 	year = String.valueOf(dateTime.getYear());
		
		String month = String.valueOf(dateTime.getMonthOfYear());
		
		String  daty = String.valueOf(dateTime.getDayOfMonth());
		
		param.put("${year}", year);
		
		param.put("${month}", month);
		
		param.put("${daty}", daty);
		
		ProjectService projectService = 	(ProjectService) SpringContextUtil.getBean("projectServiceImpl");
		
		//替换文本占位符,将来文本内容可做配置化，这一部分需要修改，以为最终模板还没确定下来
		Project pj = projectService.selectByPrimaryKey(pojoId);	
		
		String name = pj.getProjectName();
		
		param.put("${head}", name);
		
		param.put("${name}", name+"日报");		
		
		return param;		
	}

	
	
}
