package com.zhongda.monitor.report.utils;

import java.util.Iterator;

import org.joda.time.DateTime;

import com.zhongda.monitor.report.model.fictitious.ErrorCode;
import com.zhongda.monitor.report.model.fictitious.ProjectPara;
/**
 * 验证是否支持生成报告
 * @author huchao
 *
 */
public class VerificationReport {

	
	/**
	 * 报告生成条件检查
	 */
	
	public static String whetherCreateReportCongfig(String pojoId){
		
		String create = null;
		
		//用来判断该项目开关是否开启
				if(!ReportConfigOpUtils.verifyreportConfig(pojoId)){
					
					create =  ErrorCode.ERROR1;
					
				}else{
					//这个时在服务器启动时就加载了的数据，用来验证该项目下的监测参数是否支持生成报告
					//List<ProjectPara> ProjectParas = ReportConfigOpUtils.projectPara;
								
					Iterator<ProjectPara>  ite = ReportConfigOpUtils.projectPara.iterator();
					
					while(ite.hasNext()){
						
						ProjectPara projectPara = ite.next();
						
						if(pojoId.equals(String.valueOf(projectPara.getProject_id()))){
							
							if(!ReportConfigOpUtils.verifyReportPara(String.valueOf(projectPara.getMonitor_type()))){
								
								create =  ErrorCode.ERROR2;
								
							};
							
						};
						
					};
					
				}	
		
		return create;
		
	}
	/**
	 * 用户下载报告时间的验证,不支持超出当前日期下载报告
	 */
	public  static String whetherCreateReportTime(String time){
		
		DateTime dateTime  = new DateTime(time);
		
		String massage = null;
		
		if(!dateTime.isBeforeNow()){//如果参数time超过当前日期，返回错误提示信息
			
			 massage = ErrorCode.ERROR4;
		}
		
		return massage;
		
	}
}
