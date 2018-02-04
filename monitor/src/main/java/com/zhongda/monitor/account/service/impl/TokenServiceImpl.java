package com.zhongda.monitor.account.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zhongda.monitor.account.service.TokenService;

/**
 * Title: Token的业务实现类 Description: 管理 Token
 * 
 * @Author dengzm
 * @Date 2018年1月11日 下午4:13:48
 */
@Service
public class TokenServiceImpl implements TokenService{

	/** 将token存储到JVM内存(ConcurrentHashMap)中 */
	private static Map<String, String> tokenMap = new ConcurrentHashMap<String, String>();

	static final String HEADER_STRING = "Authorization";// 存放Token的Header Key
	
	/**
	 * Token部分加密密钥(本地类中)
	 */
	private static final String JWT_SECRET = "ZhongDaMonitor";

	/**
	 * Token默认过期时间(单位毫秒)
	 */
	private static final int JWT_EXP = 7 * 24 * 60 * 60 * 1000; //7天

	/**
	 * Token部分加密密钥(配置文件中)
	 */
	@Value("${jwt.secret}")
	private static String secret;
	
	/*public static void main(String[] args) throws JsonProcessingException {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", 1);
		map.put("userName", "xxxx");
		String token = createToken(map);
		System.out.println(token);
		Jws<Claims> parseToken = parseToken(token);
		ObjectMapper objectMapper = new ObjectMapper();
		String asString = objectMapper.writeValueAsString(parseToken);
		System.out.println(asString);
	}*/
	
	@Override
	public String createToken(Map<String, Object> claims) {
		long nowMillis = System.currentTimeMillis();
		Date nowDate = new Date(nowMillis);
		SecretKey key = generalKey();
		JwtBuilder builder = Jwts.builder()
				.setIssuedAt(nowDate)
				.setSubject(claims.get("userName").toString())
				.addClaims(claims)
				.setHeaderParam("typ", "JWT")
				.signWith(SignatureAlgorithm.HS256, key);		
		setExpTime(nowMillis, builder, claims.get("jwtExpTime"));
		return builder.compact();
	}

	/**
	 * 设置token过期时间
	 * @param nowMillis   生成token的时间
	 * @param builder     JwtBuilder对象
	 * @param jwtExpTime  过期时间(单位毫秒)
	 */
	private static void setExpTime(long nowMillis, JwtBuilder builder,
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

	//@Override
	public boolean checkToken(String token) {
		return null != token && token.length() != 0
				&& tokenMap.containsKey(token);
	}
	
	 public static boolean isValid(String token, Key key) {
         try {
             Jwts.parser().setSigningKey(key).parseClaimsJws(token.trim());
             return true;
         } catch (Exception e) {
             e.printStackTrace();
             return false;
         }
     }
	 
	 /*public String refreshToken(String token) {
	        final Claims claims = parseToken(token).getBody();
	        //claims.put(CLAIM_KEY_CREATED, new Date());
	        //return generateToken(claims);
	    }*/

	//@Override
	public void deleteToken(String token) {
		tokenMap.remove(token);
	}

	@Override
	public SecretKey generalKey() {
		String stringKey;
		if(null != secret){
			stringKey = secret + JWT_SECRET;
		}else{
			stringKey = secret + JWT_SECRET;
		}
		System.out.println(stringKey);
		byte[] encodedKey = Base64.getDecoder().decode(stringKey);
		SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length,
				"AES");
		return key;
	}

	@Override
	public Claims parseToken(String token) {
		SecretKey key = generalKey();
		Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
		return claims;
	}
}
