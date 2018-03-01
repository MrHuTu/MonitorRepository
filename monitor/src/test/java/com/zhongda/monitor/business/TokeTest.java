package com.zhongda.monitor.business;

import io.jsonwebtoken.impl.TextCodec;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhongda.monitor.account.model.User;

public class TokeTest {

	public static void main(String[] args) throws IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		String payload = TextCodec.BASE64URL.decodeToString("eNpskL1qwzAQgF-l3OyEk2JZP1PXZuiSjlpEIlqBLTuWTBpCoHseI4VOGQt-oMbPUVvu2Om47_vg4E7gTARFGJFCMoE8gy7Ydh1qv4mt86-g4KQTe9ppUKtsXp5NZcdVg9lVzmsYcWNCONTtVPmuLCfyVvu5IqJAkXOZFzy1tjKunA0SwiSlQjzu98ttXSU_zsb4Yyp--tv982u4fgzflyRba8p_7m9HHu2L-xMUCV-gXFD2QKTKmVrRlIVoYhdSMtyu977XcIYM7HszvYEiQyyQn38BAAD__w");
		System.out.println(payload);
		System.out.println("----------------------------------------------");
		JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Map.class, String.class, Object.class);
		Map<String, Object> claims = objectMapper.readValue(payload, javaType);
		String userJsonString = (String) claims.get("userJsonString");
		System.out.println(userJsonString);
		User user = objectMapper.readValue(userJsonString, User.class);
		System.out.println(user.getPassword());
	}
}
