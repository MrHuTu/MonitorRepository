package com.zhongda.monitor.business.mapper;

import com.zhongda.monitor.business.model.PullLineDisplacementDataShow;

public interface PullLineDisplacementDataShowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PullLineDisplacementDataShow record);

    int insertSelective(PullLineDisplacementDataShow record);

    PullLineDisplacementDataShow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PullLineDisplacementDataShow record);

    int updateByPrimaryKey(PullLineDisplacementDataShow record);
}