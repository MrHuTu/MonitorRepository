package com.zhongda.monitor.business.service;


public interface WeatherService {
	
	/**
	 * 获取城市当前日期的天气信息
	 * @param CityName 传入的城市名
	 * @return 返回天气信息
	 */
	String getWeather(String cityName);
}
