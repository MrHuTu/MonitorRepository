package com.zhongda.monitor.report.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhongda.monitor.report.model.ReportData;

public interface ReportDataMapper {
	/**
	 * 	查询当前时间段数据是否已经归档               
	 * 			
	 * @return
	 */
 public List<ReportData> selectReportData(@Param("sensor_number")String sensor_number,@Param("smu_channel")String smu_channel,@Param("smu_number")String smu_number,@Param("current_times")String current_times);
 
 
}
