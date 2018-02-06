package com.zhongda.monitor.account.service;

import io.jsonwebtoken.Claims;

import java.util.Map;

import org.springframework.mobile.device.Device;

/**
 * Title: Token 业务接口
 * Description: 验证token的有效性，解析token等操作
 * @Author dengzm
 * @Date 2018年1月11日 下午4:13:23
 */
public interface TokenService {
	
	/**
	 * 创建一个token
	 * @param claims 添加到token中的信息
	 * @return 返回创建好的token
	 */
	String createToken(Map<String, Object> claims);
	
	/**
	 * 创建一个token
	 * @param claims 添加到token中的信息
	 * @param device device检测访问主体
	 * @return 返回创建好的token
	 */
	String createToken(Map<String, Object> claims, Device device);
	
	/**
     * 解析token
     * @param token 需解析的token
     * @return 返回token中的信息
     */
    Claims parseToken(String token);
    
    /**
     * 刷新token,改变token创建时间
     * @param token 需刷新的token
     * @return 返回刷新之后的token
     */
    String refreshToken(String token);
    
	/**
	 * 检查token是否失效
	 * @param token 需检查的token
	 * @return true 有效,false 无效
	 */
    boolean checkToken(String token);  
    
    /**
	 * 使当前token失效
	 */
    void deleteToken(String token); 
}
