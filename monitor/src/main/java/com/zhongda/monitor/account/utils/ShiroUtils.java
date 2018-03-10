package com.zhongda.monitor.account.utils;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.crypto.hash.SimpleHash;

import com.zhongda.monitor.account.exception.NoStatelessTokenException;
import com.zhongda.monitor.account.mapper.UserMapper;
import com.zhongda.monitor.account.model.User;
import com.zhongda.monitor.core.utils.CacheUtils;
import com.zhongda.monitor.core.utils.SpringUtils;

/**
 * 类ShiroUtils的功能描述:
 * Shiro工具类
 * @auther dengzm
 * @date 2018-01-10 16:19:35
 */
public class ShiroUtils {
	
	/**  加密算法 */
	public final static String ALGORITHM_NAME = "MD5";
	
	/** 加密散列次数 */
	public static final int HASH_ITERATIONS= 1024;
	
	private static final Logger logger = Logger.getLogger(ShiroUtils.class);
	
	private static UserMapper userMapper = SpringUtils.getBean(UserMapper.class);
	
	/**
	 * 加密密码（使用默认的MD5摘要加密，散列值默认为1024）
	 * @param password 需加密的密码
	 * @param salt 盐
	 * @return
	 */
	public static String encryptPassword(String password, String salt) {
		return new SimpleHash(ALGORITHM_NAME, password, salt, HASH_ITERATIONS).toString();
	}
	
	/**
	 * 获取当前登录用户（shiro框架 user）
	 * @return
	 */
	public static User getCurrentUser() {
		String token = TokenUtils.getToken();
		return getUserFromToken(token);
	}
	
	/**
	 * 从给定的token中获取当前登录用户（shiro框架 user）
	 * @return
	 */
	public static User getUserFromToken(String token) {
		if(null == token){
			throw new NoStatelessTokenException("token失效或用户已退出，请重新登录认证");
		}
		Map<String, Object> claims = TokenUtils.getClaimsFromeToken(token);
		String userName = (String) claims.get("userName");
		return userMapper.selectByUserName(userName);
	}
	
	/**
	 * 判断是否登录（shiro框架 ）
	 * @return
	 */
	public static boolean isLogin() {
		String token = TokenUtils.getToken();
		return isLogin(token);
	}
	
	/**
	 * 根据token判断是否登录（shiro框架 ）
	 * @param token 传过来的token
	 * @return
	 */
	public static boolean isLogin(String token) {
		//获取缓存中的token
		Object obj = CacheUtils.get(CacheUtils.CACHE_TOKEN, token);
		if(null == obj){
			return false;
		}
		return true;
	}
	
	/**
	 * 注销（shiro框架 ）
	 */
	public static void logout() {
		String token = TokenUtils.getToken();
		//移除缓存中的token
		CacheUtils.remove(CacheUtils.CACHE_TOKEN, token);
		logger.debug("清除Token: " + token);
	}
	
}
