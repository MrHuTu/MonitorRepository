package com.zhongda.monitor.core.mapper;

import com.zhongda.monitor.core.model.SysCode;

public interface SysCodeMapper {
    int deleteByPrimaryKey(Integer scId);

    int insert(SysCode record);

    int insertSelective(SysCode record);

    SysCode selectByPrimaryKey(Integer scId);

    int updateByPrimaryKeySelective(SysCode record);

    int updateByPrimaryKey(SysCode record);
}