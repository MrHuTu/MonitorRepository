package com.zhongda.monitor.business.service;

import java.util.List;

import com.zhongda.monitor.business.model.Alarm;

/**
 * Title : 告警 业务 接口
 * Description : 处理告警的增删改查操作
 * @Author dengzm
 * @Date 2018年1月26日 下午3:43:38
 */
public interface AlarmService {
	
	/**
	 * 根据查询条件分页查询出当前用户下的告警信息
	 * @param alarm 封装了查询条件的alarm对象(包含当前页和每页记录数)
	 * @return
	 */
	List<Alarm> selectPageAlarmByQuery(Alarm alarm);

}
