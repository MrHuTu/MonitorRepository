package com.zhongda.monitor.business.service;

import java.util.List;

import com.zhongda.monitor.business.model.Reminder;

public interface ReminderService {
	
	/**
	  * 查询出距离现在某段时间内发生的所有提醒信息
	  */
	List<Reminder> selectReminderPeriod();
}
