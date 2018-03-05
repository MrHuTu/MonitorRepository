package com.zhongda.monitor.business.service;

import java.util.List;

import com.zhongda.monitor.business.model.StatisticChart;

public interface StatisticChartService {
	List<StatisticChart> selectByPojoId(int poJoId);
}
