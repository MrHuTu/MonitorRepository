package com.zhongda.monitor.business.model.fictitious;

import java.io.Serializable;

/**
 * 天气数据模型
 * @author chao.hu
 * 2018年3月23日14:31:57
 *
 */
public class Weather implements Serializable{
String temperature ="";
String weather ="";
String wind ="";
String week ="";
String date ="";
/*String weather_id = "";*/
public String getTemperature() {
	return temperature;
}
public void setTemperature(String temperature) {
	this.temperature = temperature;
}
public String getWeather() {
	return weather;
}
public void setWeather(String weather) {
	this.weather = weather;
}
public String getWind() {
	return wind;
}
public void setWind(String wind) {
	this.wind = wind;
}
public String getWeek() {
	return week;
}
public void setWeek(String week) {
	this.week = week;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	//date = String.format("YYYY-MM-dd", date);
	this.date = date;
}

/*public String getWeather_id() {
	return weather_id;
}
public void setWeather_id(String weather_id) {
	this.weather_id = weather_id;
}*/
@Override
public String toString() {
	return "Weather [temperature=" + temperature + ", weather=" + weather
			+ ", wind=" + wind + ", week=" + week + ", date=" + date + "]";
}

}
