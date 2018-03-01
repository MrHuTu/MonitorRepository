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
	 * @param claims 添加到token中的信息,Map集合
	 * @param password 用户密码(作为加密的密钥)
	 * @return 返回创建好的token
	 */
	String createToken(Map<String, Object> claims, String password);
	
	/**
	 * 创建一个token
	 * @param data 添加到token中的信息,json格式字符串
	 * @param password 用户密码(作为加密的密钥)
	 * @return 返回创建好的token
	 */
	String createToken(String userJsonData, String password); 
	
	/**
	 * 创建一个token
	 * @param claims 添加到token中的信息,Map集合
	 * @param device device检测访问主体
	 * @param password 用户密码(作为加密的密钥)
	 * @return 返回创建好的token
	 */
	String createToken(Map<String, Object> claims, Device device, String password);
	
	/**
	 * 创建一个token
	 * @param data 添加到token中的信息,json格式字符串
	 * @param device device检测访问主体
	 * @param password 用户密码(作为加密的密钥)
	 * @return 返回创建好的token
	 */
	String createToken(String userJsonData, Device device, String password); 
	
	/**
     * 解析token
     * @param token 需解析的token
     * @param password 用户密码(作为解密的密钥)
     * @return 返回token中的信息
     */
    Claims parseToken(String token, String password);
    
    /**
     * 刷新token,改变token创建时间
     * @param token 需刷新的token
     * @param password 用户密码(作为加密解密的密钥)
     * @return 返回刷新之后的token
     */
    String refreshToken(String token, String password);
    
	/**
	 * 检查token是否有效
	 * @param token 需检查的token
	 * @param password 用户密码(作为解密的密钥)
	 */
    boolean checkToken(String token, String password);  
    
    /**
	 * 使当前token失效
	 * @param password 用户密码(作为加密解密的密钥)
	 */
    void deleteToken(String token, String password);
}
