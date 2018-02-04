package com.zhongda.monitor.business.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhongda.monitor.business.mapper.AlarmMapper;
import com.zhongda.monitor.business.model.Alarm;
import com.zhongda.monitor.business.model.Project;
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

	@Override
	public List<Alarm> selectAlarmAndLinkmanPeriod() {
		return alarmMapper.selectAlarmAndLinkmanPeriod();
	}

	@Override
	public List<Alarm> selectPageAlarmByUserIdAndNotConfirm(Integer userId, Integer pageNum, Integer pageSize) {
		//PageHelper.startPage(pageNum, pageSize);
		return alarmMapper.selectAlarmByUserIdAndNotConfirm(userId);
	}

	@Override
	public List<Alarm> selectPageAlarmByQuery(Alarm alarm) {
		//PageHelper.startPage(alarm.getPageNum(), alarm.getPageSize());
		return alarmMapper.selectPageAlarmByQuery(alarm);
	}

	@Override
	public List<Alarm> selectAlarmByProjectId(Integer projectId) {
		return alarmMapper.selectAlarmByProjectId(projectId);
	}

	@Override
	public List<Alarm> selectAlarmWithAlarmCount(Project project) {
		if (null != project.getPageNum() && null != project.getPageSize()) {
			//PageHelper.startPage(project.getPageNum(), project.getPageSize());
		}
		return alarmMapper.selectAlarmWithAlarmCount(project);
	}

	@Override
	public Result<Alarm> updateAlarmStatus(Integer alarmId) {
		int index = alarmMapper.updateAlarmStatus(alarmId);
		Result<Alarm> result = new Result<Alarm>();
		if(index > 0){
			result.setCode(Result.SUCCESS);
			result.setMsg("修改状态成功");
		}else{
			result.setCode(Result.FAILURE);
			result.setMsg("修改状态失败");
		}
		return result;
	}

	@Override
	public Result<Alarm> updateBatchAlarmStatus(String alarmIds) {
		List<Integer> list = new ArrayList<Integer>();
		String[] alarmIdArray = alarmIds.split(",");
		Result<Alarm> result = new Result<Alarm>();
		for (String alarmId : alarmIdArray) {
			if(null != alarmId && !alarmId.trim().equals("")){
				list.add(Integer.parseInt(alarmId));
			}
		}
		int index = alarmMapper.updateBatchAlarmStatus(list);
		if(index > 0){
			result.setCode(Result.SUCCESS);
			result.setMsg("修改状态成功");
		}else{
			result.setCode(Result.FAILURE);
			result.setMsg("修改状态失败");
		}		
		return result;
	}

	@Override
	public Result<Alarm> updateBatchAlarmStatusByQuery(Alarm alarm) {
		int index = alarmMapper.updateBatchAlarmStatusByQuery(alarm);
		Result<Alarm> result = new Result<Alarm>();
		if(index > 0){
			result.setCode(Result.SUCCESS);
			result.setMsg("修改状态成功");
		}else{
			result.setCode(Result.FAILURE);
			result.setMsg("修改状态失败");
		}		
		return result;
	}
}
