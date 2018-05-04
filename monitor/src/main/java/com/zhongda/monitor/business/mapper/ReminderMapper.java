package com.zhongda.monitor.business.mapper;

import java.util.List;

import com.zhongda.monitor.business.model.Reminder;

public interface ReminderMapper {
    int deleteByPrimaryKey(Integer reminderId);

    int insert(Reminder record);

    int insertSelective(Reminder record);

    Reminder selectByPrimaryKey(Integer reminderId);

    int updateByPrimaryKeySelective(Reminder record);

    int updateByPrimaryKey(Reminder record);
    
    /**
     * 查询出距离现在某段时间内发生的所有提醒信息
     * @return
     */
	List<Reminder> selectReminderPeriod();
}