package com.zhongda.monitor.report.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhongda.monitor.report.mapper.SideTableDataMapper;
import com.zhongda.monitor.report.model.fictitious.ReportWeekData_Head;
import com.zhongda.monitor.report.model.fictitious.SideTableData;
import com.zhongda.monitor.report.service.SideTableDataService;
import com.zhongda.monitor.report.utils.GitYmlParaUtils;
import com.zhongda.monitor.report.utils.ReportDateUtils;
/**
 * 查询水平位移，和沉降的数据，用来填充word文档报告
 * @author 胡超 2018年4月18日19:15:07
 *2018年6月5日17:21:12补充（将该实现指定为全部报表数据查询的实现类）
 */
@Service
public class SideTableDataServiceImpl implements SideTableDataService {
	@Autowired
	SideTableDataMapper sideTableDataMapper;
	@Autowired
	GitYmlParaUtils gitYmlParaUtils;
	@Override
	public List<SideTableData> selectSideTableData(int pojoId,String time) {
		
		return sideTableDataMapper.selectSideTableData(pojoId, time);
		
	}
	/**
	 * 取收敛，沉降周报数据
	 */
	@Override
	
	public List<SideTableData> selectSideTableDataOfWeek() {
		
		Map<String, String>  sAnde = ReportDateUtils.getTimeInterval(new Date());
				
		String[] times = gitYmlParaUtils.getSetweek().split("\\;");
		
		return sideTableDataMapper.selectSideTableDataOfWeek(sAnde.get("begin"),sAnde.get("end"),times[0],times[1]);
	}
	
	@Override
	public List<ReportWeekData_Head> selectReportOfWeek(String projectId) {
		
		Map<String, String>  sAnde = ReportDateUtils.getTimeInterval(new Date());
		
		String[] times = gitYmlParaUtils.getSetweek().split("\\;");
		
		return sideTableDataMapper.selectReportOfWeek(projectId, sAnde.get("begin"),sAnde.get("end"),times[0],times[1]);
	}
	
	@Test
	public void  test(){
		
		List<ReportWeekData_Head> a = selectReportOfWeek("176");
		
		for(ReportWeekData_Head v: a){
			
			System.out.println(v);
			
		}
	}
}
