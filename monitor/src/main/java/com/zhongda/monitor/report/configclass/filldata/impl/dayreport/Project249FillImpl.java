package com.zhongda.monitor.report.configclass.filldata.impl.dayreport;

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

@Component("Project249FillImpl")
public class Project249FillImpl implements FillBasics{
	
	private  Logger logger = LoggerFactory.getLogger(Project249FillImpl.class);
	
	@Autowired
	SideTableDataService sideTableDataService;

	@Override
	public void fillData(XWPFDocument doc2, String pojoId, String time) {
		
		String downTime = "%"+time+"%";
		
		List<SideTableData> sideTableDatas = sideTableDataService.selectSideTableData(Integer.parseInt(pojoId),downTime);
		
		logger.info("sideTableDatas大小："+sideTableDatas.size());
		
		
			
		logger.info(pojoId+",sideTableDatas.size()="+sideTableDatas.size());
		
		
		
			//生成表格---竖向位移  16
			FillWordMapUtils.verticalDisplacement(doc2,sideTableDatas,"${tablea}","16",ReportConfig.COVERGENCE);
			
		
	}

}
