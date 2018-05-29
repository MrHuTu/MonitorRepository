package com.zhongda.monitor.report.configclass.filldata.impl.weekreport;

import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhongda.monitor.report.configclass.ReportConfig;
import com.zhongda.monitor.report.configclass.filldata.FillBasics;
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
		synchronized (this) {
			
			List<SideTableData> sideTableDatas = sideTableDataService.selectSideTableDataOfWeek();
			
			logger.info("sideTableDatas大小："+sideTableDatas.size());
			
			System.out.println(sideTableDatas);
				
			logger.info(pojoId+",sideTableDatas.size()="+sideTableDatas.size());
			
			
			
				//生成表格---竖向位移  16
				FillWordMapUtils.verticalDisplacement(doc2,sideTableDatas,"${tableb}","16",ReportConfig.WEEK_SEDIMENTATION);
				
			
				//生成表格---水平位移  17
				FillWordMapUtils.verticalDisplacement(doc2,sideTableDatas,"${tablea}","15",ReportConfig.WEEK_SEDIMENTATION);
				
				
			
				
		}		
		
	}

}
