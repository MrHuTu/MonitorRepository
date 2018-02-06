package com.zhongda.monitor.core.service;

import java.util.List;

import com.zhongda.monitor.core.model.SysLog;

/**
 * 类SysLogService的功能描述:
 * 系统日志
 * @auther dengzm
 * @date 2018-01-10 15:34:27
 */
public interface SysLogService {
	
	SysLog selectSysLogById(Long sysLogId);
	
	List<SysLog> selectAllSysLog();
	
	int insertSysLog(SysLog sysLog);
	
	int updateSysLog(SysLog sysLog);
	
	int deleteSysLogById(Long sysLogId);
	
	int deleteBatchSysLog(Long[] sysLogIds);
}