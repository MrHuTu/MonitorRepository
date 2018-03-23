package com.zhongda.monitor.business.service;

import java.util.ArrayList;

import com.zhongda.monitor.business.model.fictitious.Weather;

public interface WeatherService {
	
	ArrayList<Weather> getWeather(String CityName);
}
