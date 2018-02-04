package com.zhongda.monitor.business.service;

import java.util.List;

import com.zhongda.monitor.business.model.AlarmLinkman;

/**
 * Title : 告警联系人 业务 接口
 * Description : 处理告警联系人的增删改查操作
 * @Author dengzm
 * @Date 2018年1月26日 下午3:44:22
 */
public interface AlarmLinkmanService {

	/**
	 * 查询所有数据
	 * 
	 * @return
	 */
	List<AlarmLinkman> selectAll();

	/**
	 * 批量插入AlarmLinkman对象
	 * 
	 * @param alarList
	 * @return
	 */
	int insertAlarmLinkmanList(List<AlarmLinkman> alarList);

	/**
	 * 修改联系人状态
	 * 
	 * @param status
	 * @param alarmLinkmanId
	 * @return
	 */
	int updateStatusByalarmLinkmanId(Integer status, Integer alarmLinkmanId);

	/**
	 * 删除联系人通过ID
	 * 
	 * @param alarmLinkmanId
	 * @return
	 */
	int deleteByPrimaryKey(Integer alarmLinkmanId);
}
