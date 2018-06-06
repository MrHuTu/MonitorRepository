package com.zhongda.monitor.report.configclass.filldata.impl.weekreport;

import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhongda.monitor.report.configclass.ReportConfig;
import com.zhongda.monitor.report.configclass.filldata.FillBasics;
import com.zhongda.monitor.report.model.fictitious.ReportWeekData_Head;
import com.zhongda.monitor.report.model.fictitious.SideTableData;
import com.zhongda.monitor.report.service.SideTableDataService;
import com.zhongda.monitor.report.utils.FillWordMapUtils;


@Component("Project176FillImpl_Week")
public class Project176FillImpl_Week implements FillBasics{
	
	private  Logger logger = LoggerFactory.getLogger(Project176FillImpl_Week.class);
	
	@Autowired
	SideTableDataService sideTableDataService;
	@Override
	public void fillData(XWPFDocument doc2,String pojoId,String time) {							
			
		List<ReportWeekData_Head> ReportWeekDatas = sideTableDataService.selectReportOfWeek(pojoId);
		
		for(ReportWeekData_Head v: ReportWeekDatas ){
			
			System.out.println(v);
			
		}
			
		
								
		
	}
	
	//当下载周报师会调用该方法,为了保持依旧实现FillBasics接口的定义规则，此处做个方法代理
	public void fillData(XWPFDocument doc2,String pojoId){
		
		fillData(doc2,pojoId,null);
		
	}

}
