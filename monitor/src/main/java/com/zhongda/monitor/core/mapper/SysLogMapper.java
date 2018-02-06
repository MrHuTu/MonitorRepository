package com.zhongda.monitor.core.mapper;

import java.util.List;

import com.zhongda.monitor.core.model.SysLog;

public interface SysLogMapper {
    int deleteByPrimaryKey(Long slId);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Long slId);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);

	List<SysLog> selectAllSysLog();
}