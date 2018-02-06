package com.zhongda.monitor.business.mapper;

import com.zhongda.monitor.business.model.Reminder;

public interface ReminderMapper {
    int deleteByPrimaryKey(Integer reminderId);

    int insert(Reminder record);

    int insertSelective(Reminder record);

    Reminder selectByPrimaryKey(Integer reminderId);

    int updateByPrimaryKeySelective(Reminder record);

    int updateByPrimaryKey(Reminder record);
}