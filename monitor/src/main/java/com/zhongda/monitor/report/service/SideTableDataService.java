package com.zhongda.monitor.report.service;

import java.util.List;

import com.zhongda.monitor.report.model.fictitious.SideTableData;

public interface SideTableDataService {
	public List<SideTableData> selectSideTableData(int pojoId);
}
