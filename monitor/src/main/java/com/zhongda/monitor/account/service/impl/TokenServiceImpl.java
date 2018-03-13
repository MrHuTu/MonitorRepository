package com.zhongda.monitor.account.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

import java.util.Base64;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhongda.monitor.account.service.TokenService;
import com.zhongda.monitor.core.model.Result;
import com.zhongda.monitor.core.utils.CacheUtils;

/**
 * Title: Token的业务实现类
 * Description: 管理 Token
 * @Author dengzm
 * @Date 2018年1月11日 下午4:13:48
 */
@Service
public class TokenServiceImpl implements TokenService{
	
	private final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);
	
	@Resource
	private ObjectMapper objectMapper;
	
	/** 未知 */
	private static final String AUDIENCE_UNKNOWN = "unknown";
	/** PC端  */
    private static final String AUDIENCE_WEB = "web";
    /** 手机 */
    private static final String AUDIENCE_MOBILE = "mobile";
    /** 平板  */
    private static final String AUDIENCE_TABLET = "tablet";
	/** Token部分加密密钥(本地类中) */
	private static final String JWT_SECRET = "ZhongDaMonitor";
	/** Token默认过期时间7天(单位毫秒) */
	private static final int JWT_EXP = 7 * 24 * 60 * 60 * 1000;
	
	@Override
	public String createToken(Map<String, Object> claims, String password) {
		return createJsonWebToken(claims, null, password);
	}

	@Override
	public String createToken(Map<String, Object> claims, Device device, String password) {
		return createJsonWebToken(claims, device, password);
	}
	
	/**
	 * 创建一个JsonWebToken
	 * @param claims 添加到token中的信息
	 * @param device device检测访问主体
	 * @return 返回创建好的token
	 */
	private String createJsonWebToken(Map<String, Object> claims, Device device, String password) {
		long nowMillis = System.currentTimeMillis();
		Date nowDate = new Date(nowMillis);
		SecretKey key = getKey(password);
		JwtBuilder builder = Jwts.builder().setIssuedAt(nowDate);
		if(null != device){
			builder.setAudience(getAudience(device));
		}
		// 使用默认过期时间
		if (JWT_EXP >= 0) {
			long expMillis = nowMillis + JWT_EXP;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}
		String token = builder.addClaims(claims) //claims中如果存在同名的参数，则会覆盖上面设置的参数
			   .signWith(SignatureAlgorithm.HS256, key)
			   .compact();
		return token;
	}
	
	/**
	 * 获取密钥
	 * @param password 使用密码加密后作为密钥
	 */
	private SecretKey getKey(String password) {
		String stringKey = JWT_SECRET + password;
		byte[] encodedKey = Base64.getDecoder().decode(stringKey);
		SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
		return key;
	}
	
	/**
     * 通过spring-mobile-device的device检测访问主体
     */
    private String getAudience(Device device) {
        String audience = AUDIENCE_UNKNOWN;
        if (device.isNormal()) {
            audience = AUDIENCE_WEB;//PC端
        } else if (device.isTablet()) {
            audience = AUDIENCE_TABLET;//平板
        } else if (device.isMobile()) {
            audience = AUDIENCE_MOBILE;//手机
        }
        return audience;
    }
	
	@Override
	public Claims parseToken(String token, String password) {
		SecretKey key = getKey(password);
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
	}
	
	@Override
	public String refreshToken(String token, String password) {
        final Claims claims = parseToken(token, password);
        claims.setExpiration(DateTime.now().plusMillis(JWT_EXP).toDate());
        JwtBuilder builder = Jwts.builder()
      		  .addClaims(claims) //claims中如果存在同名的参数，则会覆盖上面设置的参数
      		  .signWith(SignatureAlgorithm.HS256, getKey(password));
        //将旧token从缓存中移除
      	CacheUtils.remove(CacheUtils.CACHE_TOKEN, token);
      	String refreshToken = builder.compact();
        //将新token放入缓存中
      	CacheUtils.put(CacheUtils.CACHE_TOKEN, refreshToken, Result.SUCCESS);
        return refreshToken;
	}

	@Override
	public boolean checkToken(String token, String password) {
		try {
			Jwts.parser().setSigningKey(getKey(password)).parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException e) {
        	logger.error("JWT token已过期..." + e.getMessage());
        } catch (SignatureException e) {
			logger.error("无效的JWT签名..." + e.getMessage());
        } catch (MalformedJwtException e) {
        	logger.error("无效的JWT token..." + e.getMessage());
        } catch (UnsupportedJwtException e) {
        	logger.error("不支持的JWT token..." + e.getMessage());
        } catch (IllegalArgumentException e) {
        	logger.error("JWT token的组成处理无效..." + e.getMessage());
        } catch (Exception e) {
			logger.error("token令牌无效, 验证失败..." + e.getMessage());
		}
		return false;
	}
}
