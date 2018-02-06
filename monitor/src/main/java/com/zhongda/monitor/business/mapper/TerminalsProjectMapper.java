package com.zhongda.monitor.business.mapper;

import com.zhongda.monitor.business.model.TerminalsProject;

public interface TerminalsProjectMapper {
    int deleteByPrimaryKey(Integer tpId);

    int insert(TerminalsProject record);

    int insertSelective(TerminalsProject record);

    TerminalsProject selectByPrimaryKey(Integer tpId);

    int updateByPrimaryKeySelective(TerminalsProject record);

    int updateByPrimaryKey(TerminalsProject record);
}