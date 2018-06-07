package com.zhongda.monitor.report.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.joda.time.DateTime;

import com.zhongda.monitor.business.model.Project;
import com.zhongda.monitor.business.service.ProjectService;
import com.zhongda.monitor.report.model.ReportContentDay;
import com.zhongda.monitor.report.model.fictitious.BasicsModel;
import com.zhongda.monitor.report.service.ReportContentDayService;

/**
 * 用来填充文本占位符的map集合,和填充表格数据的通用类，和一些工具方法
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
	
	/**
	 * 
	 * @param pojoId
	 * @param time
	 * @return
	 */
	public static Map<String,Object> getFillContentMapD(String pojoId,String time){
		
		ReportContentDayService  ReportContentDayServiceImpl = (ReportContentDayService) SpringContextUtil.getBean("ReportContentDayServiceImpl");		
		
		List<ReportContentDay> reportContentDays = 	ReportContentDayServiceImpl.selectDayConfigById(pojoId);
				
		Map<String,Object> map =  getFillMap(pojoId,time);
		
		String name =  (String) map.get("${name}");				
		
		String pname =  name.substring(0, name.indexOf("日报"));
		
		map.put("${pname}", pname);
		
		if(reportContentDays.size()>0) {
			
			GitYmlParaUtils gitYmlParaUtils = (GitYmlParaUtils)SpringContextUtil.getBean("GitYmlParaUtils");
			
			String osPath  = gitYmlParaUtils.accordingOsGetParm("upload");
			
			
			ReportContentDay reportContentDay = reportContentDays.size()>0?reportContentDays.get(0):null;
		
			if(reportContentDay==null) return map;			
			
			map.put("${report_write}", reportContentDay.getReportWrite());
			
			map.put("${project_survey}", reportContentDay.getProjectSurvey());
			
			map.put("${project_achievement}", reportContentDay.getProjectAchievement());
			
			map.put("${project_suggest}", reportContentDay.getProjectSuggest());
			
			map.put("${remark}", reportContentDay.getRemark());
			
			map.put("${monitor_poit_pic}", osPath+reportContentDay.getMonitorPoitPic());
			
			
			map.put("${total_laser_change_pic}", osPath+reportContentDay.getTotalLaserChangePic());
			
			map.put("${current_laser_change_pic}", osPath+reportContentDay.getCurrentLaserChangePic());
			
			map.put("${speed_change_pic}", osPath+reportContentDay.getSpeedChangePic());
			
			
		}
						
		return map;
				
	}
	public static Map<String,Object> getFillContentMapW(String pojoId,String time){
		
		Map<String,Object> map =  getFillMap(pojoId,time);
		
		return map;
		
	}
	/**
	 * 
	 * @param doc2
	 * @param pojoId
	 * @param singe 占位符标记
	 * @param paraTyp 在线监测参数类型
	 */
	public static void verticalDisplacement(XWPFDocument doc2,List<? extends BasicsModel> basicsModel,String singe ,String paraTyp,String tableClass){
		
		int count = 0;
		
		Map<String, List<String>> singeMap = new HashMap<String, List<String>>();
		
		List<String> singeList = new ArrayList<String>();
		 
		List<Object> dataList = new ArrayList<Object>();
		 
		List<Object> Obj = screenTyp(basicsModel,paraTyp);
					
		Iterator<Object> ite = Obj.iterator();
			
			while(ite.hasNext()){
				
				int temp = count;
				
				int j = 0;
				
				for(int i=count;i<temp+2;i++){
					
					singeList.add("${tab"+i+"}");
					
					j++;
					
					count++;
					
					if(j==2) break;
					
				}
				
				
				
				BasicsModel objModel = (BasicsModel)ite.next();
				
				objModel.unifyLength();
				
				dataList.add(objModel);
			}
			
	
		
		
		
		singeMap.put(singe, singeList);
		
		Map<String, Object> allDatas = Wordl2007Utis.insertTabSinge(doc2, singeMap, dataList,"D");
		
		Wordl2007Utis.insertTab(doc2, allDatas, tableClass);
				
		
	}

	
	/* * 将集合下面的数据按照监测类型分类
	 * @param sideTableDatas
	 * @param poTyp
	 * @return*/
	 
	public static List<Object> screenTyp(List<? extends BasicsModel> sideTableDatas,String poTyp){
		
		
		List<Object> mapTyp = new  ArrayList<Object>();
		
		
		Iterator<? extends BasicsModel> sideTableDataList = sideTableDatas.iterator();
		
		
		while(sideTableDataList.hasNext()){
			
			BasicsModel objModel =sideTableDataList.next();						
			
			if(objModel.getTyp().equals(poTyp)){
				
				mapTyp.add(objModel);
				
			}
		}
		return mapTyp;
		
	}
	
	
	/**
	 * 格式化数据,保留2位小数
	 */
	public  static String formData(double num){
		
		DecimalFormat df = new DecimalFormat("0.00");
		 
		 //设置四舍五入模式
		df.setRoundingMode(RoundingMode.HALF_UP);
		 
		
		return  df.format(num).toString();
		
	}
}
