package com.zhongda.monitor.business.mapper;

import com.zhongda.monitor.business.model.LaserDataShow;

public interface LaserDataShowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LaserDataShow record);

    int insertSelective(LaserDataShow record);

    LaserDataShow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LaserDataShow record);

    int updateByPrimaryKey(LaserDataShow record);
}