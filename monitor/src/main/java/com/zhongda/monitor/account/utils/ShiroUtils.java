package com.zhongda.monitor.account.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;

import com.zhongda.monitor.account.model.User;

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
	 * 获取当前登录主体（shiro框架 subject）
	 * @return
	 */
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}
	
	/**
	 * 获取当前登录用户（shiro框架 user）
	 * @return
	 */
	public static User getUser() {
		return (User) SecurityUtils.getSubject().getPrincipal();
	}
	
	/**
	 * 判断是否登录（shiro框架 ）
	 * @return
	 */
	public static boolean isLogin() {
		return getUser() != null && getSubject().isAuthenticated();
	}
	
	/**
	 * 登录（shiro框架 ）
	 */
	public static void login(AuthenticationToken shiroToken) {
		SecurityUtils.getSubject().login(shiroToken);
	}
	
	/**
	 * 注销（shiro框架 ）
	 */
	public static void logout() {
		SecurityUtils.getSubject().logout();
	}
}
