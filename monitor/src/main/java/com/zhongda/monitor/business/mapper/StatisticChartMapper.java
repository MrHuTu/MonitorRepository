package com.zhongda.monitor.business.mapper;

import com.zhongda.monitor.business.model.StatisticChart;

public interface StatisticChartMapper {
    int deleteByPrimaryKey(Integer statisticChartId);

    int insert(StatisticChart record);

    int insertSelective(StatisticChart record);

    StatisticChart selectByPrimaryKey(Integer statisticChartId);

    int updateByPrimaryKeySelective(StatisticChart record);

    int updateByPrimaryKey(StatisticChart record);
}