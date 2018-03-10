package com.zhongda.monitor.account.mapper;

import org.springframework.cache.annotation.Cacheable;

import com.zhongda.monitor.account.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    @Cacheable(cacheNames = "userCache")
	User selectByUserName(String userName);
}