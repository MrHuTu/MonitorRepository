package com.zhongda.monitor.business.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhongda.monitor.business.model.AlarmLinkman;

public interface AlarmLinkmanMapper {
	int deleteByPrimaryKey(Integer alarmLinkmanId);

	int insert(AlarmLinkman record);

	int insertSelective(AlarmLinkman record);

	AlarmLinkman selectByPrimaryKey(Integer alarmLinkmanId);

	int updateByPrimaryKeySelective(AlarmLinkman record);

	int updateByPrimaryKey(AlarmLinkman record);

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
	int updateStatusByalarmLinkmanId(@Param(value = "status") Integer status,
			@Param(value = "alarmLinkmanId") Integer alarmLinkmanId);
}