package com.zhongda.monitor.core.service.impl;

import javax.annotation.Resource;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import com.zhongda.monitor.core.service.CacheService;

/**
 * Title :缓存管理实现类
 * Description : 处理缓存的增删改查操作
 * @Author dengzm
 * @Date 2018年1月16日 下午3:04:10
 */
//@Service
public class CacheServiceImpl implements CacheService{
	
	@Resource(name = "shiroEhcacheManager")
	private CacheManager cacheManager;
	
	@Override
	public void setPasswordCache(String email, String password) {
		Cache<String, String> passwordCache = cacheManager.getCache("passwordCache");
		passwordCache.put(email, password);
	}

	@Override
	public String getPasswordCache(String email) {
		Cache<String, String> passwordCache = cacheManager.getCache("passwordCache");
		return passwordCache.get(email);
	}
	

}
