package com.zhongda.monitor.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhongda.monitor.business.mapper.ReminderMapper;
import com.zhongda.monitor.business.model.Reminder;
import com.zhongda.monitor.business.service.ReminderService;

@Service
public class ReminderServiceImpl implements ReminderService{
	
	@Resource
	private ReminderMapper reminderMapper;
	
	@Override
	public List<Reminder> selectReminderPeriod() {
		return reminderMapper.selectReminderPeriod();
	}

}
