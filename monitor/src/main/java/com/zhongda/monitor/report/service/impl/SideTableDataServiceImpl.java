package com.zhongda.monitor.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhongda.monitor.report.mapper.SideTableDataMapper;
import com.zhongda.monitor.report.model.fictitious.SideTableData;
import com.zhongda.monitor.report.service.SideTableDataService;
/**
 * 查询水平位移，和沉降的数据，用来填充word文档报告
 * @author 胡超 2018年4月18日19:15:07
 *
 */
@Service
public class SideTableDataServiceImpl implements SideTableDataService {
	@Autowired
	SideTableDataMapper sideTableDataMapper;
	@Override
	public List<SideTableData> selectSideTableData(int pojoId) {
		
		return sideTableDataMapper.selectSideTableData(pojoId);
		
	}

}
