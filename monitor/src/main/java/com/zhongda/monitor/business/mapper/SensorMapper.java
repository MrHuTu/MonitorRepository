package com.zhongda.monitor.business.mapper;

import com.zhongda.monitor.business.model.Sensor;

public interface SensorMapper {
    int deleteByPrimaryKey(Integer sensorId);

    int insert(Sensor record);

    int insertSelective(Sensor record);

    Sensor selectByPrimaryKey(Integer sensorId);

    int updateByPrimaryKeySelective(Sensor record);

    int updateByPrimaryKey(Sensor record);
}