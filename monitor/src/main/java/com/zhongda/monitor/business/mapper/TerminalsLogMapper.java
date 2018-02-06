package com.zhongda.monitor.business.mapper;

import com.zhongda.monitor.business.model.TerminalsLog;

public interface TerminalsLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TerminalsLog record);

    int insertSelective(TerminalsLog record);

    TerminalsLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TerminalsLog record);

    int updateByPrimaryKey(TerminalsLog record);
}