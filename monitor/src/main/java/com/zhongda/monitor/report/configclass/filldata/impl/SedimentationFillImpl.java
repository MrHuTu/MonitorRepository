package com.zhongda.monitor.report.configclass.filldata.impl;

import java.util.Iterator;
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
	public void fillData(XWPFDocument doc2,String pojoId) {
		System.out.println("******************************调用成功***************************************"+pojoId);
		List<SideTableData> a = sideTableDataService.selectSideTableData(Integer.parseInt(pojoId));
		Iterator<SideTableData> b = a.iterator();
		while(b.hasNext()){
			SideTableData  c = b.next();
			System.out.println(c);
		}
	}

}
