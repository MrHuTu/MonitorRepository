package com.zhongda.monitor.account.service;

import java.util.Map;

import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;

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
	 * 检查token是否失效
	 * @param token 需检查的token
	 * @return true 有效,false 无效
	 */
    boolean checkToken(String token); 
    
    /**
     * 删除token
     * @param token 需删除的token
     */
    void deleteToken(String token);
    
    /**
     *  生成加密key
     */
    SecretKey generalKey();
    
    /**
     * 解析token
     * @param token 需解析的token
     * @return 返回token中的信息
     */
    Claims parseToken(String token);
}
