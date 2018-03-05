package com.zhongda.monitor.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhongda.monitor.business.mapper.StatisticChartMapper;
import com.zhongda.monitor.business.model.StatisticChart;
import com.zhongda.monitor.business.service.StatisticChartService;
@Service
public class StatisticChartServiceImpl implements  StatisticChartService{
	@Autowired
	StatisticChartMapper statisticChartMapper;
	@Override
	public List<StatisticChart> selectByPojoId(int poJoId) {
		
		return statisticChartMapper.selectByPojoId(poJoId);
		
	}

}
