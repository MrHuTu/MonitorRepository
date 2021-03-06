package com.zhongda.monitor.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhongda.monitor.business.mapper.AlarmLinkmanMapper;
import com.zhongda.monitor.business.model.AlarmLinkman;
import com.zhongda.monitor.business.service.AlarmLinkmanService;
@Service
/**
 * 告警联系业务类
 * @author KCheng
 *
 */
public class AlarmLinkmanServiceImpl implements AlarmLinkmanService {
	
	@Resource
	private AlarmLinkmanMapper alarmLinkmanMapper;
	public List<AlarmLinkman> selectAlarmLinkMan() {
		return alarmLinkmanMapper.selectAlarmLinkMan();
	}
}
