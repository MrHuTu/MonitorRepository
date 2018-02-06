package com.zhongda.monitor.business.mapper;

import com.zhongda.monitor.business.model.Terminals;

public interface TerminalsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Terminals record);

    int insertSelective(Terminals record);

    Terminals selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Terminals record);

    int updateByPrimaryKey(Terminals record);
}