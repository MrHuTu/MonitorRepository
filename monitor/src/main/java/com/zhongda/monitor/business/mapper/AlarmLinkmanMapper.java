package com.zhongda.monitor.business.mapper;

import com.zhongda.monitor.business.model.AlarmLinkman;

public interface AlarmLinkmanMapper {
    int deleteByPrimaryKey(Integer alarmLinkmanId);

    int insert(AlarmLinkman record);

    int insertSelective(AlarmLinkman record);

    AlarmLinkman selectByPrimaryKey(Integer alarmLinkmanId);

    int updateByPrimaryKeySelective(AlarmLinkman record);

    int updateByPrimaryKey(AlarmLinkman record);
}