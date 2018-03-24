package com.zhongda.monitor.core;

import io.jsonwebtoken.Claims;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import com.zhongda.monitor.account.service.TokenService;
import com.zhongda.monitor.account.service.impl.TokenServiceImpl;
import com.zhongda.monitor.core.utils.SystemClock;


public class Hehe {
	
	public static void main(String[] args) {
		//TestCurrentTime();
		System.out.println(DateTime.now().toString("YYYYMMdd"));
	}

	public static void TestCurrentTime() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000000; i++) {
			System.currentTimeMillis();
		}
		long end = System.currentTimeMillis();
		System.out.println("调用System.currentTimeMillis 耗时 ： " + (end - start));
		long start1 = SystemClock.now();
		for (int i = 0; i < 10000; i++) {
			System.out.println(SystemClock.now());
		}
		System.out.println(System.currentTimeMillis());
		long end1 = SystemClock.now();
		System.out.println("调用SystemClock.now 耗时 ： " + (end1 - start1));
	}

	public static void XXX(String[] args) {
		TokenService tokenService = new TokenServiceImpl();
		
		String token = createToken(tokenService);
		try {
			Thread.sleep(1000*10);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			refreshToken(tokenService, token);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println();
		}
		
		System.out.println("---------------------重新解析第一次生成的token-------------------------");
		Claims claims5 = tokenService.parseToken(token, "123456");
		System.out.println(claims5);
		System.out.println("当前时间" + System.currentTimeMillis());
		System.out.println();
		
		System.out.println("===========================================================");
		
		System.out.println(DateTime.now().plusDays(7));
	}
	
	private static String createToken(TokenService tokenService) {
		System.out.println("-------------------第一次生成token---------------------------");
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("userId", 8);
		claims.put("userName", "admin");
		String token = tokenService.createToken(claims, "123456");
		System.out.println(token);
		System.out.println("当前时间" + System.currentTimeMillis());
		System.out.println();
		
		System.out.println("---------------------解析第一次生成的token-------------------------");
		Claims claims2 = tokenService.parseToken(token, "123456");
		System.out.println(claims2);
		System.out.println("当前时间" + System.currentTimeMillis());
		System.out.println();
		
		return token;
	}

	private static void refreshToken(TokenService tokenService, String token) {
		System.out.println("----------------------重新刷新第一次生成的token--------------------------");
		String token2 = tokenService.refreshToken(token, "123456");
		System.out.println(token2);
		System.out.println("当前时间" + System.currentTimeMillis());
		System.out.println();
		
		System.out.println("---------------------解析刷新后的token-------------------------");
		Claims claims3 = tokenService.parseToken(token2, "123456");
		System.out.println(claims3);
		System.out.println("当前时间" + System.currentTimeMillis());
		System.out.println();
	}

}
