package com.zhongda.monitor.business.mapper;

import com.zhongda.monitor.business.model.StaticLevelDataShow;

public interface StaticLevelDataShowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StaticLevelDataShow record);

    int insertSelective(StaticLevelDataShow record);

    StaticLevelDataShow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StaticLevelDataShow record);

    int updateByPrimaryKey(StaticLevelDataShow record);
}