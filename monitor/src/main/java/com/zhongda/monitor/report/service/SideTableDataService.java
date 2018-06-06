package com.zhongda.monitor.report.service;

import java.util.List;

import com.zhongda.monitor.report.model.fictitious.ReportWeekData_Head;
import com.zhongda.monitor.report.model.fictitious.SideTableData;
/**
 * 查询报表数据的接口
 * @author huchao
 *2018年6月5日17:20:56
 */
public interface SideTableDataService {
	public List<SideTableData> selectSideTableData(int pojoId,String time);
	
	public List<SideTableData> selectSideTableDataOfWeek();
	
	public  List<ReportWeekData_Head> selectReportOfWeek(String projectId);
	
	
}
