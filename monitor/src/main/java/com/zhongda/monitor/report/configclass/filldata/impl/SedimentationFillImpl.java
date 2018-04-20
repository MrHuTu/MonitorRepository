package com.zhongda.monitor.report.configclass.filldata.impl;

import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhongda.monitor.report.configclass.filldata.SedimentationFill;
import com.zhongda.monitor.report.model.fictitious.SideTableData;
import com.zhongda.monitor.report.service.SideTableDataService;
@Service("SedimentationFillImpl")
public class SedimentationFillImpl implements SedimentationFill {
	@Autowired
	SideTableDataService sideTableDataService;
	@Override
	public void fillData(XWPFDocument doc2,String pojoId,String time) {		
		String downTime = "%"+time+"%";
		List<SideTableData> sideTableDatas = sideTableDataService.selectSideTableData(Integer.parseInt(pojoId),downTime);
		
		
		for(int i=0;i<sideTableDatas.size();i++){
			System.out.println(sideTableDatas.get(i));
		}
		
		
	}
	
}
