package com.zhongda.monitor.account.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zhongda.monitor.account.exception.NoStatelessTokenException;
import com.zhongda.monitor.account.mapper.UserMapper;
import com.zhongda.monitor.account.model.User;
import com.zhongda.monitor.core.model.Result;
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
	 * 
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
	 * 获取当前请求的userName的值
	 * @return 返回userName
	 */
	public static String getUserName(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return getUserNameFromRequest(request);
	}
	
	/**
	 * 获取请求头中的userName的值
	 * @param request 请求头
	 * @return 返回userName
	 */
	public static String getUserNameFromRequest(HttpServletRequest request){
		String userName = request.getParameter("userName");
		if(userName == null){
			userName = request.getParameter("username");
			if(userName == null){
				userName = request.getParameter("UserName");
			}
		}
		return userName;
	}
	
	/**
	 * 获取请求登录的用户
	 */
	public static User getUnLoginUser(){
		String userName = getUserName();
		if(null == userName){
			return null;
		}
		return userMapper.selectByUserName(userName);
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
		if(null == userName){
			throw new NoStatelessTokenException("token无效或已失效，请重新登录认证");
		}
		return userMapper.selectByUserName(userName);
	}
	
	/**
	 * 判断是否注销（shiro框架 ）
	 * @return
	 */
	public static boolean isLogout() {
		String token = TokenUtils.getToken();
		return isLogout(token);
	}
	
	/**
	 * 根据token判断是否注销（shiro框架 ）
	 * @param token 传过来的token
	 * @return
	 */
	public static boolean isLogout(String token) {
		//获取缓存中的token
		Object obj = CacheUtils.get(CacheUtils.CACHE_TOKEN, token);
		return (obj != null) ? true : false;
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
		return (token != null && !"".equals(token.trim())) ? true : false;
	}
	
	
	/**
	 * 注销（shiro框架 ）
	 * @throws InterruptedException 
	 */
	public static void logout() {
		String token = TokenUtils.getToken();
		Map<String, Object> claims = TokenUtils.getClaimsFromeToken(token);
		long expTime = (Integer) claims.get(TokenUtils.CLAIM_KEY_EXPIRATiON);
		long nowTime = (System.currentTimeMillis()) / 1000;
		//将该token加入缓存，并设置过期时间
		CacheUtils.putAndSetTimeToIdle(CacheUtils.CACHE_TOKEN, token, Result.SUCCESS, (int)(expTime - nowTime));
		logger.debug("清除Token: " + token);
	}
	
}
