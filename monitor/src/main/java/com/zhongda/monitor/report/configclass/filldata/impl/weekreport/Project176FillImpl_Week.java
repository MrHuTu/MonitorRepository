package com.zhongda.monitor.report.configclass.filldata.impl.weekreport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhongda.monitor.report.configclass.ReportConfig;
import com.zhongda.monitor.report.configclass.filldata.FillBasics;
import com.zhongda.monitor.report.model.fictitious.ReportWeekData_Head;
import com.zhongda.monitor.report.service.SideTableDataService;
import com.zhongda.monitor.report.utils.Wordl2007Utis;


@Component("Project176FillImpl_Week")
public class Project176FillImpl_Week implements FillBasics{
	
	//private  Logger logger = LoggerFactory.getLogger(Project176FillImpl_Week.class);
	
	@Autowired
	SideTableDataService sideTableDataService;
	@Override
	public void fillData(XWPFDocument doc2,String pojoId,String time) {		
		int count = 0;
				
		Map<String, List<String>> singeMap = new HashMap<String, List<String>>();
			
		
		List<String> singeList = new ArrayList<String>();
		
		List<Object> dataList = new ArrayList<Object>();
			
		List<ReportWeekData_Head> reportWeekDatas = sideTableDataService.selectReportOfWeek(pojoId);
						
		for(int i=0;i<reportWeekDatas.size();i++){
			
			dataList.add(reportWeekDatas.get(i));
			
		}
						
		//确定表格生成的个数
		int dataSize = reportWeekDatas.size();
		
		int  confirmTableCount =  dataSize%5==0?dataSize/5:((dataSize/5)+1);
		
		//插入占位符
		for(int i=0;i<confirmTableCount;i++){
			
			
			int temp = count;
			
			int j = 0;
			
			for(int f=count;f<temp+2;f++){
				
				singeList.add("${tab"+f+"}");
				
				j++;
				
				count++;
				
				if(j==2) break;
				
			}
		
					
		}
		
		
		singeMap.put("${tablea}", singeList);
		
		Map<String, Object> allDatas = Wordl2007Utis.insertTabSinge(doc2, singeMap, dataList,"W");
		
		Wordl2007Utis.insertTab(doc2, allDatas, ReportConfig.WEEK_SEDIMENTATION_BANK);
		
			
		
	}
	
	//当下载周报师会调用该方法,为了保持依旧实现FillBasics接口的定义规则，此处做个方法代理
	public void fillData(XWPFDocument doc2,String pojoId){
		
		fillData(doc2,pojoId,null);
		
	}

}
