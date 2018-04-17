package com.zhongda.monitor.report.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhongda.monitor.report.model.ReportData;

public interface MigrationDataMapper {
	/**
	 * 查询报告数据
	 */
	
	public List<ReportData> selectRepotrData( @Param("tableName") String tableName, @Param("beginTime") String beginTime, @Param("endTime")String endTime);
	/**
	 * 数据迁移
	 */

	public void insertData( @Param("reportDatas") List<ReportData> reportDatas) ;
}
