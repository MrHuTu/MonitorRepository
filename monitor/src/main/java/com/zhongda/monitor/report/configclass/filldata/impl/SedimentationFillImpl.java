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

/**对应项目 261  262  263  264
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
		
		String downTime = "%"+time+"%";
		
		List<SideTableData> sideTableDatas = sideTableDataService.selectSideTableData(Integer.parseInt(pojoId),downTime);
		
		logger.info("sideTableDatas大小："+sideTableDatas.size());
		
		
			
		logger.info(pojoId+",sideTableDatas.size()="+sideTableDatas.size());
		
		
		
		//生成表格---竖向位移  16
		FillWordMapUtils.verticalDisplacement(doc2,sideTableDatas,"${tablea}","16",ReportConfig.COVERGENCE);
		
		//生成表格---水平位移  17
		FillWordMapUtils.verticalDisplacement(doc2,sideTableDatas,"${tableb}","17",ReportConfig.COVERGENCE);
		
	}
	/**
	 * 
	 * @param doc2
	 * @param pojoId
	 * @param singe 占位符标记
	 * @param paraTyp 在线监测参数类型
	 *//*
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
				
				
				
				SideTableData sideTableData = ite.next();
				
				sideTableData.unifyLength();
				
				dataList.add(sideTableData);
			}
			
	
		
		
		
		singeMap.put(singe, singeList);
		
		Map<String, Object> allDatas = Wordl2007Utis.insertTabSinge(doc2, singeMap, dataList);
		
		Wordl2007Utis.insertTab(doc2, allDatas, ReportConfig.COVERGENCE);
		
		//int count = sideTableDatasList.size();
		
		
		
	}

	*//**
	 * 将集合下面的数据按照监测类型分类
	 * @param sideTableDatas
	 * @param poTyp
	 * @return
	 *//*
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
		
	}*/
	
	
}
