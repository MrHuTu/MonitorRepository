package com.zhongda.monitor.report.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhongda.monitor.report.model.fictitious.SideTableData;
/**
 * 查询水平位移，和沉降的数据，用来填充word文档报告
 * @author 胡超  2018年4月18日19:06:37
 *
 */
public interface SideTableDataMapper {
	
	public List<SideTableData> selectSideTableData(@Param("pojoId")int pojoId);
	
}
