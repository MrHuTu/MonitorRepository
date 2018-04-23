package com.zhongda.monitor.business.service;

import java.util.List;

import com.zhongda.monitor.business.model.AlarmLinkman;

/**
 * 告警联系人  业务接口
 * 
 * @author KCheng
 *
 */
public interface AlarmLinkmanService {
	/**
	 * 查询告警信息联系人相关信息
	 * 
	 */
	List <AlarmLinkman> selectAlarmLinkMan();
	
	
	
}
