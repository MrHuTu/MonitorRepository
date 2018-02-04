package com.zhongda.monitor.core.service;

/**
 * Title : 缓存管理接口
 * Description : 处理缓存的增删改查操作
 * @Author dengzm
 * @Date 2018年1月16日 下午2:59:27
 */
public interface CacheService {
	
	/**
	 * 添加邮箱，密码到缓存中
	 * @param email 邮箱
	 * @param password 密码
	 */
	void setPasswordCache(String email, String password);
	
	/**
	 * 根据邮箱从缓存中获取密码
	 * @param email 邮箱
	 * @return 密码
	 */
	String getPasswordCache(String email);
}
