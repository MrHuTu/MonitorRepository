package com.zhongda.monitor.business.service.impl;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.zhongda.monitor.business.exception.NoWeatherException;
import com.zhongda.monitor.business.service.WeatherService;
import com.zhongda.monitor.business.utils.WeatherUtils;
import com.zhongda.monitor.core.utils.CacheUtils;

/**
 * 调用天气接口 地区的天气数据以时间和地区名称为key缓存在Cache中，减少接口的调用次数 缓存有效数据时间 一天
 * @author chao.hu
 */
@Service
public class WeatherServiceImpl implements WeatherService {

	
	@Override
	public String getWeather(String cityName) {
		String weatherString = null;
		//以城市名和当前查询日期做key
		String key = cityName + DateTime.now().toString("YYYYMMdd");
		//从缓存中获取数据
		Object obj = CacheUtils.get("weatherCache", key);
		//如果缓存中没有对应的数据
		if (null == obj) {
			//则调用天气接口获取天气信息，并将天气信息存到缓存中
			weatherString = WeatherUtils.getTodayWeather(cityName);
			if(null == weatherString){
				throw new NoWeatherException("调用天气接口失败,获取不到天气信息");
			}
			CacheUtils.put("weatherCache", key, weatherString);
		} else { //如果缓存中存在对应的数据，则从缓存中取天气信息
			weatherString = obj.toString();
		}
		return weatherString;
	}

}
