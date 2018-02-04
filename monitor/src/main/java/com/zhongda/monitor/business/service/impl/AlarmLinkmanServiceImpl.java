package com.zhongda.monitor.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhongda.monitor.business.mapper.AlarmLinkmanMapper;
import com.zhongda.monitor.business.model.AlarmLinkman;
import com.zhongda.monitor.business.service.AlarmLinkmanService;

/**
 * Title : 告警联系人Service实现类
 * Description : 处理告警联系人的增删改查操作
 * @Author dengzm
 * @Date 2018年1月26日 下午3:46:14
 */
@Service
public class AlarmLinkmanServiceImpl implements AlarmLinkmanService {

	@Resource
	private AlarmLinkmanMapper alarmLinkmanMapper;

	@Override
	public List<AlarmLinkman> selectAll() {
		return alarmLinkmanMapper.selectAll();
	}

	@Override
	public int insertAlarmLinkmanList(List<AlarmLinkman> alarList) {
		return alarmLinkmanMapper.insertAlarmLinkmanList(alarList);
	}

	@Override
	public int updateStatusByalarmLinkmanId(Integer status,
			Integer alarmLinkmanId) {
		return alarmLinkmanMapper.updateStatusByalarmLinkmanId(status,
				alarmLinkmanId);
	}

	@Override
	public int deleteByPrimaryKey(Integer alarmLinkmanId) {
		return alarmLinkmanMapper.deleteByPrimaryKey(alarmLinkmanId);
	}

}
