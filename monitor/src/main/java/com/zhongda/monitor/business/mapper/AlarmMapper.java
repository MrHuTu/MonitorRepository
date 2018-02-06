package com.zhongda.monitor.business.mapper;

import java.util.List;

import com.zhongda.monitor.business.model.Alarm;

public interface AlarmMapper {
    int deleteByPrimaryKey(Integer alarmId);

    int insert(Alarm record);

    int insertSelective(Alarm record);

    Alarm selectByPrimaryKey(Integer alarmId);

    int updateByPrimaryKeySelective(Alarm record);

    int updateByPrimaryKey(Alarm record);

	List<Alarm> selectPageAlarmByQuery(Alarm alarm);
}