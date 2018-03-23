package com.zhongda.monitor.business.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.zhongda.monitor.business.model.fictitious.Weather;
import com.zhongda.monitor.business.service.WeatherService;
import com.zhongda.monitor.business.util.WeatherUtil;
import com.zhongda.monitor.core.utils.CacheUtils;
/**
 * 调用天气接口
 * 地区的天气数据以时间和地区名称为key缓存在Cache中，减少接口的调用次数
 * @author chao.hu
 *
 */
@Component
public class WeatherServiceImpl implements WeatherService {
	 private static SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Weather> getWeather(String CityName) {
		ArrayList<Weather>  listWeather = null;
		Date  date = new Date();
		String  time = sdf.format(date);
		String key = CityName+time;
		Object  obj= CacheUtils.get("Weather", key);
		if(obj!=null){
			  listWeather= (ArrayList<Weather>)obj;
			  System.out.println("天气数据出自缓存");
		}else{
			try {
				listWeather = WeatherUtil.getTodayWeather(CityName);
				System.out.println("天气数据出自接口");
				CacheUtils.put("Weather", key, listWeather);
			} catch (NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listWeather;
	}

}
