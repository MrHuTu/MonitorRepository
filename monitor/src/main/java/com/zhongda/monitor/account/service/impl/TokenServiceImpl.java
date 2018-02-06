package com.zhongda.monitor.account.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Base64;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Service;

import com.zhongda.monitor.account.service.TokenService;

/**
 * Title: Token的业务实现类
 * Description: 管理 Token
 * @Author dengzm
 * @Date 2018年1月11日 下午4:13:48
 */
@Service
public class TokenServiceImpl implements TokenService{
	
	private final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);
	
	/** 创建时间标志 */
	static final String CLAIM_KEY_CREATED = "created"; 
	/** 过期时间标志 */
	static final String CLAIM_KEY_EXPIRATiON = "jwtExpTime";
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
	/** Token部分加密密钥(配置文件中) */
	@Value("${jwt.secret}")
	private String secret;
	
	@Override
	public String createToken(Map<String, Object> claims) {
		return createJsonWebToken(claims, null);
	}

	@Override
	public String createToken(Map<String, Object> claims, Device device) {
		return createJsonWebToken(claims, device);
	}
	
	/**
	 * 创建一个JsonWebToken
	 * @param claims 添加到token中的信息
	 * @param device device检测访问主体
	 * @return 返回创建好的token
	 */
	private String createJsonWebToken(Map<String, Object> claims, Device device) {
		long nowMillis = System.currentTimeMillis();
		System.out.println(nowMillis);
		Date nowDate = new Date(nowMillis);
		SecretKey key = getKey();
		JwtBuilder builder = Jwts.builder()
				.setIssuedAt(nowDate)
				.addClaims(claims)
				.setHeaderParam("typ", "JWT")
				.signWith(SignatureAlgorithm.HS256, key);
		if(null != device){
			builder.setAudience(getAudience(device));
		}
		setExpTime(nowMillis, builder, claims.get(CLAIM_KEY_EXPIRATiON));
		return builder.compact();
	}

	/**
	 * 设置token过期时间
	 * @param nowMillis   生成token的时间
	 * @param builder     JwtBuilder对象
	 * @param jwtExpTime  过期时间(单位毫秒)
	 */
	private void setExpTime(long nowMillis, JwtBuilder builder,
			Object jwtExpTime) {
		if (null != jwtExpTime) { // 自定义了过期时间，使用自定义过期时间
			long jwtExpTime_ = (long) jwtExpTime;
			if (jwtExpTime_ >= 0) {
				long expMillis = nowMillis + jwtExpTime_;
				Date exp = new Date(expMillis);
				builder.setExpiration(exp);
			}
		} else { // 如果没有自定义过期时间，则使用默认过期时间
			if (JWT_EXP >= 0) {
				long expMillis = nowMillis + JWT_EXP;
				Date exp = new Date(expMillis);
				builder.setExpiration(exp);
			}
		}
	}
	
	/**
	 * 获取加密的密钥
	 */
	private SecretKey getKey() {
		String stringKey;
		if(null != secret){
			stringKey = secret + JWT_SECRET;
		}else{
			stringKey = JWT_SECRET;
		}
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
	public Claims parseToken(String token) {
		SecretKey key = getKey();
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
	}
	
	@Override
	public String refreshToken(String token) {
        final Claims claims = parseToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return createToken(claims);
	}

	@Override
	public boolean checkToken(String token) {
		try {
			Jwts.parser().setSigningKey(getKey()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			logger.error("Token 已经失效... :" + e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public void deleteToken(String token) {
		 final Claims claims = parseToken(token);
		 claims.put(CLAIM_KEY_EXPIRATiON, 0);
		 createToken(claims);
	}	
}
