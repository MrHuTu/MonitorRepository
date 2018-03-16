package com.zhongda.monitor.account.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.Cacheable;

import com.zhongda.monitor.account.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);
    //修改用户信息
    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    @Cacheable(cacheNames = "userCache")
	User selectByUserName(String userName);
	/**
	 * 验证输入的邮箱手机号是否有账户与之对应
	 * 
	 */
	public User validateUserByPhone(@Param("phone") String phone);
	
	/**
	 * 验证输入的邮箱是否有账户与之对应
	 * @param email 
	 * @return
	 */
	public User validateUserByEmail(@Param("email") String email);
	
}