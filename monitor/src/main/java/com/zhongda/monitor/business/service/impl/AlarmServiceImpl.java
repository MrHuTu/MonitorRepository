package com.zhongda.monitor.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhongda.monitor.business.mapper.AlarmMapper;
import com.zhongda.monitor.business.model.Alarm;
import com.zhongda.monitor.business.service.AlarmService;

/**
 * Title : 告警Service实现类
 * Description : 处理告警的增删改查操作
 * @Author dengzm
 * @Date 2018年1月26日 下午3:45:08
 */
@Service
public class AlarmServiceImpl implements AlarmService{
	
	@Resource
	private AlarmMapper alarmMapper;

	@Override
	public List<Alarm> selectPageAlarmByQuery(Alarm alarm) {
		//PageHelper.startPage(alarm.getPageNum(), alarm.getPageSize());
		return alarmMapper.selectPageAlarmByQuery(alarm);
	}




}
