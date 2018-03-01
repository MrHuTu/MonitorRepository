package com.zhongda.monitor.account.utils;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.impl.TextCodec;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhongda.monitor.account.exception.NoStatelessTokenException;
import com.zhongda.monitor.account.model.User;

/**
 * Title : TokenUtils管理
 * Description : Token工具类
 * @Author dengzm
 * @Date 2018年3月1日 下午3:42:40
 */
public class TokenUtils {
	
	private static final Logger logger = Logger.getLogger(TokenUtils.class);
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	/**
	 * 解析token，返回token的payload部分
	 * @param token
	 * @return
	 */
	private static String parseTokenBody(String token){
		if(null == token){
			throw new NoStatelessTokenException("没有token令牌,验证失败...");
		}else{
			String[] tokenArray = token.split("\\.");
			if(tokenArray.length != 3){
				throw new MalformedJwtException("token令牌格式错误,验证失败...");
			}else{
				String payload = TextCodec.BASE64URL.decodeToString(tokenArray[1]);
				return payload;
			}
		}
	}
	
	/**
	 * 获取token中荷载信息
	 * @param token 传入的token
	 * @return Claims Map集合
	 */
    public static Map<String, Object> getClaimsFromeToken(String token){
    	String tokenBody = parseTokenBody(token);
    	JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Map.class, String.class, Object.class);
    	try {
			return objectMapper.readValue(tokenBody, javaType);
		} catch (IOException e) {
			logger.error("解析token的荷载部分失败,解码后的json字符串不能转换成Map集合" + e.getMessage());
			return null;
		}
	}
	
    /**
     * 获取token中的用户信息
     * @param token 传入的token
     * @return User user对象
     */
	public static User getUserFromeToken(String token){
		Map<String, Object> claims = getClaimsFromeToken(token);
		String userJsonString = (String) claims.get("userJsonString");
		try {
			return objectMapper.readValue(userJsonString, User.class);
		} catch (IOException e) {
			logger.error("解析token的荷载部分的用户信息失败失败,json字符串不能转换成User对象" + e.getMessage());
			return null;
		}
	}
}
