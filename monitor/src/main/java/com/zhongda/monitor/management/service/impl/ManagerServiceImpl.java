package com.zhongda.monitor.management.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhongda.monitor.business.mapper.PublicSensorDataMapper;
import com.zhongda.monitor.business.model.fictitious.PublicSensorData;
import com.zhongda.monitor.management.model.PaginationResult;
import com.zhongda.monitor.management.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Resource
	private PublicSensorDataMapper publicSensorDataMapper;

	@Override
	public PaginationResult querySensorData(int offset, int limit,
			String tableName, String sensorNumber, String beginTimes,
			String endTimes, String smuNumber, String smuChannel) {
		Page<Object> offsetPage = PageHelper.offsetPage(offset, limit);
		List<PublicSensorData> sensorDatas = publicSensorDataMapper
				.selectSensorDataByAllContia(tableName, sensorNumber,
						beginTimes, endTimes, smuNumber, smuChannel);
		return new PaginationResult(offsetPage.getTotal(), sensorDatas);
	}

	@Override
	public List<PublicSensorData> querySmuidGroup(String tableName) {
		return publicSensorDataMapper.selectSmuidGroupBySmuId(tableName);
	}

}
