package com.zhongda.monitor.business.mapper;

import com.zhongda.monitor.business.model.Threshold;

public interface ThresholdMapper {
    int deleteByPrimaryKey(Integer thresholdId);

    int insert(Threshold record);

    int insertSelective(Threshold record);

    Threshold selectByPrimaryKey(Integer thresholdId);

    int updateByPrimaryKeySelective(Threshold record);

    int updateByPrimaryKey(Threshold record);
}