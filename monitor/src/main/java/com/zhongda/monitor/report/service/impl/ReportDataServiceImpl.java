package com.zhongda.monitor.report.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhongda.monitor.report.mapper.ReportDataMapper;
import com.zhongda.monitor.report.model.ReportData;
import com.zhongda.monitor.report.service.ReportDataService;
@Service
public class ReportDataServiceImpl implements ReportDataService {
	
	@Autowired
	ReportDataMapper reportDataMapper;
	/**
	 * @return true存在该条数据,false 不存在该数据
	 */
	@Override
	public boolean selectReportData(String sensor_number,String smu_channel, String smu_number, String current_times) {
		
		List<ReportData>  ReportDatas = reportDataMapper.selectReportData(sensor_number, smu_channel, smu_number, current_times);
		
		if(ReportDatas.size()>0){
			return true;
		}else{
			return false;
		}
	}

}
