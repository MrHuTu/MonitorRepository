package com.zhongda.monitor.report.configclass.filldata.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhongda.monitor.report.configclass.ReportConfig;
import com.zhongda.monitor.report.configclass.filldata.SedimentationFill;
import com.zhongda.monitor.report.model.fictitious.SideTableData;
import com.zhongda.monitor.report.service.SideTableDataService;
import com.zhongda.monitor.report.utils.Wordl2007Utis;

/**
 * 填充 16,17的数据
 * @author Administrator
 *
 */
@Service("SedimentationFillImpl")
public class SedimentationFillImpl implements SedimentationFill {
	@Autowired
	SideTableDataService sideTableDataService;
	@Override
	public void fillData(XWPFDocument doc2,String pojoId,String time) {		
		
		String downTime = "%"+time+"%";
		List<SideTableData> sideTableDatas = sideTableDataService.selectSideTableData(Integer.parseInt(pojoId),downTime);
		
		//生成表格---竖向位移  16
		verticalDisplacement(doc2,sideTableDatas,"${tablea}","16");
		
		//生成表格---水平位移  17
		verticalDisplacement(doc2,sideTableDatas,"${tableb}","17");
		
	}
	/**
	 * 
	 * @param doc2
	 * @param pojoId
	 * @param singe 占位符标记
	 * @param paraTyp 在线监测参数类型
	 */
	public void verticalDisplacement(XWPFDocument doc2,List<SideTableData> sideTableDatas,String singe ,String paraTyp){
		int count = 0;
		
		Map<String, List<String>> singeMap = new HashMap<String, List<String>>();
		
		List<String> singeList = new ArrayList<String>();
		 
		List<Object> dataList = new ArrayList<Object>();
		 
		List<SideTableData> sideTableDatasList = screenTyp(sideTableDatas,paraTyp);
		
		Iterator<SideTableData> ite = sideTableDatasList.iterator();
		
		while(ite.hasNext()){
			
			int temp = count;
			
			int j = 0;
			
			for(int i=count;i<temp+2;i++){
				
				singeList.add("${tab"+i+"}");
				
				j++;
				
				count++;
				
				if(j==2) break;
				
			}
			singeMap.put(singe, singeList);
			
			SideTableData sideTableData = ite.next();
			
			sideTableData.unifyLength();
			
			dataList.add(sideTableData);
		}
		
		Map<String, Object> allDatas = Wordl2007Utis.insertTabSinge(doc2, singeMap, dataList);
		
		Wordl2007Utis.insertTab(doc2, allDatas, ReportConfig.COVERGENCE);
		
		//int count = sideTableDatasList.size();
		
		
		
	}

	/**
	 * 将集合下面的数据按照监测类型分类
	 * @param sideTableDatas
	 * @param poTyp
	 * @return
	 */
	public List<SideTableData> screenTyp(List<SideTableData> sideTableDatas,String poTyp){
		
		List<SideTableData> mapTyp = new  ArrayList<SideTableData>();
		
		//int typCount = ReportConfigOpUtils.getObjrctPataCount(pojoId);
		
		Iterator<SideTableData> sideTableDataList = sideTableDatas.iterator();
		
		while(sideTableDataList.hasNext()){
			
			SideTableData sideTableData = sideTableDataList.next();						
			
			if(sideTableData.getTyp().equals(poTyp)){
				
				mapTyp.add(sideTableData);
				
			}
		}
		return mapTyp;
		
	}
	
	
}
