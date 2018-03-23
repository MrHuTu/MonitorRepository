package com.zhongda.monitor.business.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.zhongda.monitor.business.model.fictitious.Weather;

  
/** 
 * java调用中央气象局天气预报接口 
 *  
 * @author chao.hu
 * 2018年3月23日14:31:50 
 *  
 */  
@Component
public class WeatherUtil {  
  
   
  
    /** 
     *  
     * 获取实时天气1<br> 
     * 方 法 名： getTodayWeather <br> 
     *  
     * @param Cityid 
     *            城市编码 
     *            
     */  
	@Cacheable(value="Weather")
    public static ArrayList<Weather> getTodayWeather(String CityName)  
            throws IOException, NullPointerException {  
    	int i =0;
    	ArrayList<Weather> list = new ArrayList<Weather>();
    	String city = java.net.URLEncoder.encode(CityName, "UTf-8");  
    
       URL url = new URL("http://v.juhe.cn/weather/index?format=2&cityname="+city+"&key=c69d43ee74d7e28b59ee2bd92b465166");  
    	
       URLConnection connectionData = url.openConnection();  
       connectionData.setConnectTimeout(1000);  
        Map<String, Object> map = new HashMap<String, Object>();  
        try {  
            BufferedReader br = new BufferedReader(new InputStreamReader(  
                    connectionData.getInputStream(), "utf-8"));  
            StringBuilder sb = new StringBuilder();  
            String line = null;  
            while ((line = br.readLine()) != null)  
                sb.append(line);  
            String datas = sb.toString();  
          
           JSONObject jsonData = JSONObject.fromObject(datas);  
           JSONObject  a = jsonData.getJSONObject("result");
           JSONObject  today =  a.getJSONObject("today");
           JSONArray future =  a.getJSONArray("future");
       
          @SuppressWarnings({"rawtypes" })
		Iterator iterator =  future.iterator();
          while(iterator.hasNext()){
        	 
        	 JSONObject jo =  (JSONObject) iterator.next();
        	 jo.remove("weather_id");
        	  @SuppressWarnings("static-access")
        	  Weather  Weather = (Weather) jo.toBean(jo, Weather.class);       	  
        	  list.add(Weather);
        	 i++;
        	 if(i==3)  break ;
          }
        
           System.out.println(today);  
           System.out.println(future);  
        
        } catch (SocketTimeoutException e) {  
            System.out.println("连接超时");  
        } catch (FileNotFoundException e) {  
            System.out.println("加载文件出错");  
        }  
  
        return list;  
  
    }  
      
}  
 
