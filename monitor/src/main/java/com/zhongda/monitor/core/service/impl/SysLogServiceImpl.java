package com.zhongda.monitor.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhongda.monitor.core.mapper.SysLogMapper;
import com.zhongda.monitor.core.model.SysLog;
import com.zhongda.monitor.core.service.SysLogService;

@Service
public class SysLogServiceImpl implements SysLogService {
	
	@Resource
	private SysLogMapper sysLogMapper;
	
	@Override
	public SysLog selectSysLogById(Long sysLogId) {
		return sysLogMapper.selectByPrimaryKey(sysLogId);
	}

	@Override
	public List<SysLog> selectAllSysLog() {
		return sysLogMapper.selectAllSysLog();
	}

	@Override
	public int insertSysLog(SysLog sysLog) {
		return sysLogMapper.insertSelective(sysLog);
	}

	@Override
	public int updateSysLog(SysLog sysLog) {
		return sysLogMapper.updateByPrimaryKeySelective(sysLog);
	}

	@Override
	public int deleteSysLogById(Long sysLogId) {
		return sysLogMapper.deleteByPrimaryKey(sysLogId);
	}

	@Override
	public int deleteBatchSysLog(Long[] sysLogIds) {
		return 0;
	}
}
	