package com.zhongda.monitor.report.configclass.filldata.impl;

import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhongda.monitor.report.configclass.ReportConfig;
import com.zhongda.monitor.report.configclass.filldata.FillBasics;
import com.zhongda.monitor.report.model.fictitious.SideTableData;
import com.zhongda.monitor.report.service.SideTableDataService;
import com.zhongda.monitor.report.utils.FillWordMapUtils;

/**对应项目 261  262  263  264 176
 * 填充 16,17的数据
 * @author Administrator
 *
 */
@Service("SedimentationFillImpl")
public class SedimentationFillImpl implements FillBasics {
	
	private  Logger logger = LoggerFactory.getLogger(SedimentationFillImpl.class);
	
	@Autowired
	SideTableDataService sideTableDataService;
	@Override
	public void fillData(XWPFDocument doc2,String pojoId,String time) {		
		
		synchronized (this) {
			
			
			String downTime = "%"+time+"%";
			
			List<SideTableData> sideTableDatas = sideTableDataService.selectSideTableData(Integer.parseInt(pojoId),downTime);
			
			logger.info("sideTableDatas大小："+sideTableDatas.size());
			
			
				
			logger.info(pojoId+",sideTableDatas.size()="+sideTableDatas.size());
			
			
			
				//生成表格---竖向位移  16
				FillWordMapUtils.verticalDisplacement(doc2,sideTableDatas,"${tablea}","16",ReportConfig.COVERGENCE);
				
			if(pojoId.equals("176")){
				
				//生成表格---水平位移  17
				FillWordMapUtils.verticalDisplacement(doc2,sideTableDatas,"${tableb}","15",ReportConfig.COVERGENCE);
				
			}else{
				//生成表格---水平位移  17
				FillWordMapUtils.verticalDisplacement(doc2,sideTableDatas,"${tableb}","17",ReportConfig.COVERGENCE);
				
			}		
			
				
		}
		
		
		
	}
	
	
}
