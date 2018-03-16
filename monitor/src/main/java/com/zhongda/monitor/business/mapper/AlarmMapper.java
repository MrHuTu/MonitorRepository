package com.zhongda.monitor.business.mapper;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import com.zhongda.monitor.business.model.Alarm;
import com.zhongda.monitor.core.utils.CacheUtils;

public interface AlarmMapper {
    int deleteByPrimaryKey(Integer alarmId);

    int insert(Alarm record);

    int insertSelective(Alarm record);

    Alarm selectByPrimaryKey(Integer alarmId);

    int updateByPrimaryKeySelective(Alarm record);

    int updateByPrimaryKey(Alarm record);
    
	/**
	 * 分頁查询当前用户下的所有告警信息(可加查询条件)
	 * @param alarm 查询条件
	 * 
	 */
    @Cacheable(cacheNames = CacheUtils.CACHE_ALARM)
    List<Alarm> selectPageAlarmByQuery(Alarm alarm);

	/**
	 * 更改告警信息状态 
	 * @param alarmId 告警id
	 * 
	 */
	 int updateAlarmStatusByAlarmId(Integer alarmId);
	
	 /**
	  * 按条件删除告警信息
	  */
	 int deleteAlarm(Alarm alarm);
	 /**
	  * 统计当前用户下未确认的告警信息总条数
	  */
	 int alarmCount(Integer userId);
}