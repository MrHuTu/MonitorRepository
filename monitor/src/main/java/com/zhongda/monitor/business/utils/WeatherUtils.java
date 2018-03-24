package com.zhongda.monitor.business.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhongda.monitor.business.exception.NoWeatherException;

/**
 * java调用中央气象局天气预报接口
 * @author chao.hu 2018年3月23日14:31:50
 */
public class WeatherUtils {
	
	private static final Logger logger = Logger.getLogger(WeatherUtils.class);
	
	/**
	 * 获取实时天气1<br>
	 * 方 法 名： getTodayWeather <br>
	 * @param cityName 城市名称
	 */
	public static List<Object> getTodayWeather(String cityName){
		List<Object> list = null;
		try {
			String city = java.net.URLEncoder.encode(cityName, "UTf-8");
			URL url = new URL("http://v.juhe.cn/weather/index?format=2&cityname="
					+ city + "&key=c69d43ee74d7e28b59ee2bd92b465166");

			URLConnection connection = url.openConnection();
			//设置连接超时时间5秒
			connection.setConnectTimeout(5000);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null){
				sb.append(line);
			}
			//重新组装天气信息
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Map.class, String.class, Object.class);
			Map<String, Map<String, List<Object>>> weatherMap = objectMapper.readValue(sb.toString(), javaType);
			Map<String, List<Object>> result = weatherMap.get("result");
			list = (List<Object>) result.get("future");

		} catch (Exception e) {
			logger.error("调用天气接口失败", e);
			throw new NoWeatherException("调用天气接口失败" + e.getMessage());
		}
		return list;
	}
}
