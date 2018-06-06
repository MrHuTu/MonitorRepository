package com.zhongda.monitor.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongda.monitor.business.mapper.AlarmMapper;
import com.zhongda.monitor.business.model.Alarm;
import com.zhongda.monitor.business.service.AlarmService;
import com.zhongda.monitor.core.model.Result;

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

	
	public Map<String , Object> selectPageAlarmByQuery(Alarm alarm) {
		//设置分页信息
		if(alarm.getPageNum() ==null){
			alarm.setPageNum(1);
		}if(alarm.getPageSize()==null){
			alarm.setPageSize(10);
		}
		PageHelper.startPage(alarm.getPageNum(),alarm.getPageSize());
		//查询信息
		List<Alarm>alarmList= alarmMapper.selectPageAlarmByQuery(alarm);
		PageInfo<Alarm> alarmPageInfo = new PageInfo<Alarm>(alarmList);
		//返回的数据
		Map<String, Object> alarmMap = new HashMap<String, Object>();
		alarmMap.put("total", alarmPageInfo.getTotal());
		alarmMap.put("alarmList", alarmList);
		return alarmMap;
	}


	
	public Result<String>  updateAlarmStatusByAlarmId(Integer alarmId) {
		int index = alarmMapper.updateAlarmStatusByAlarmId(alarmId);
		Result<String> result = new Result<String>();
		if(index > 0){
			result.success("修改成功", "修改成功");
		}else{
			result.failure("修改状态失败", "修改状态失败");
		}
		return result;
	
	}



	@Override
	public Result<String> deleteAlarm(Alarm alarm, Integer number) {
		 int num =alarmMapper.deleteAlarm(alarm);
		 Result<String > result = new Result<String>();
		if(num != 0){
			result.success("删除成功", "删除成功");
		}else{
			result.failure("条件有误", "条件有误");
		}
		return result;
	}



	@Override
	public Result<Integer> alarmCount(Integer userId) {
		
		 Result<Integer > result = new Result<Integer>();
		 int num =alarmMapper.alarmCount(userId);
		 result.success("查询成功", num);
		 return result;
	}



	@Override
	public List<Alarm> selectAlarmAndLinkmanPeriod() {
		return alarmMapper.selectAlarmAndLinkmanPeriod();
	}




}