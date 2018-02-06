package com.zhongda.monitor.business.mapper;

import com.zhongda.monitor.business.model.UserProject;

public interface UserProjectMapper {
    int deleteByPrimaryKey(Integer upId);

    int insert(UserProject record);

    int insertSelective(UserProject record);

    UserProject selectByPrimaryKey(Integer upId);

    int updateByPrimaryKeySelective(UserProject record);

    int updateByPrimaryKey(UserProject record);
}