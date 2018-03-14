package com.zhongda.monitor.core.utils;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;

/**
 * Title : CacheUtils管理
 * Description : 处理缓存的增删改查操作
 * @Author dengzm
 * @Date 2018年3月8日 下午3:59:10
 */
public class CacheUtils{

	private static CacheManager cacheManager = (EhCacheCacheManager) SpringUtils.getBean(EhCacheCacheManager.class);

	/*
     *   |- 用户缓存（userCache）
     *       |- 按用户id存储              key: userId- + userId             value:User
     *       |- 按用户名存储 key: key: userName- + userName         value:User
     *   |- 角色缓存（roleCache）
     *       |- 按角色id存储              key:roleId- + roleId              value:Role    
     *   |- 权限缓存（permissionCache）
     *       |- 按权限id存储              key:permissionId- + permissionId  value:Permission
     *       |- 按角色id存储              key:roleId_permission- + roleId   value:List<Permission>
     *       |- 按用户id存储              key:userId_permission- + userId   value:List<Permission>
     */
    public static final String CACHE_USER = "userCache";
    public static final String CACHE_ROLE = "roleCache";
    public static final String CACHE_PERMISSION = "permissionCache";
    public static final String CACHE_TOKEN = "tokenCache";
	
	/**
	 * 获取缓存
	 */
	public static Object get(String cacheName, String key) {
		Element element = getCache(cacheName).get(key);
		return element == null ? null : element.getObjectValue();
	}

	/**
	 * 写入缓存
	 */
	public static void put(String cacheName, String key, Object value) {
		Element element = new Element(key, value);
		getCache(cacheName).put(element);
	}

	/**
	 * 从缓存中移除
	 */
	public static void remove(String cacheName, String key) {
		getCache(cacheName).remove(key);
	}
	
	/**
	 * 写入缓存的同时设置缓存的过期时间
	 */
	public static void putAndSetTimeToIdle(String cacheName, String key, Object value, int timeToLiveSeconds) {
		Element element = new Element(key, value);
		element.setTimeToLive(timeToLiveSeconds);;
		getCache(cacheName).put(element);
	}
	
	/**
	 * 修改缓存的timeToLiveSeconds
	 */
	public static void setTimeToIdle(String cacheName, String key, int timeToLiveSeconds) {
		Element element = getCache(cacheName).get(key);
		element.setTimeToLive(timeToLiveSeconds);
	}
	
	/**
	 * 通过缓存名称获取对应的缓存
	 */
	private static Ehcache getCache(String cacheName){
		Ehcache cache = ((EhCacheCacheManager) cacheManager).getCacheManager().getCache(cacheName);
		return cache;
	}
}