package com.zhongda.monitor.account.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhongda.monitor.account.exception.NoStatelessTokenException;
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
		JwtBuilder builder = Jwts.builder()
				.setIssuedAt(nowDate)
				.addClaims(claims)
				//.setHeaderParam("zip", "DEF")
				//.compressWith(CompressionCodecs.DEFLATE)//压缩，可选GZIP
				.signWith(SignatureAlgorithm.HS256, key);
		if(null != device){
			builder.setAudience(getAudience(device));
		}
		setExpTime(nowMillis, builder, claims.get(CLAIM_KEY_EXPIRATiON));
		return builder.compact();
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
	public Map<String, Object> parseTokenBody(String token){
		if(null == token){
			throw new NoStatelessTokenException("没有token令牌,验证失败...");
		}else{
			String[] tokenArray = token.split("\\.");
			if(tokenArray.length != 3){
				throw new MalformedJwtException("token令牌格式错误,验证失败...");
			}else{
				String payload = TextCodec.BASE64URL.decodeToString(tokenArray[1]);
				ObjectMapper objectMapper = new ObjectMapper();
				JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Map.class, String.class, Object.class);
				try {
					return objectMapper.readValue(payload, javaType);
				} catch (IOException e) {
					logger.error("解析token的荷载部分失败,解码后的json字符串不能转换成Map集合" + e.getMessage());
					return null;
				}
			}
		}
	}
    
	@Override
	public Claims parseToken(String token, String password) {
		SecretKey key = getKey(password);
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
	}
	
	@Override
	public String refreshToken(String token, String password) {
        final Claims claims = parseToken(token, password);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return createToken(claims, password);
	}

	@Override
	public boolean checkToken(String token, String password) {
		try {
			Jwts.parser().setSigningKey(getKey(password)).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			logger.error("token令牌无效, 验证失败..." + e.getMessage());
			return false;
		}
		
	}

	@Override
	public void deleteToken(String token, String password) {
		 final Claims claims = parseToken(token, password);
		 claims.put(CLAIM_KEY_EXPIRATiON, 0);
		 createToken(claims, password);
	}	
}
