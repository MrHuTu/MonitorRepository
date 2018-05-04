package com.zhongda.monitor.report.utils;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
/**
 * 取yml配置文件参数
 * @author Administrator
 *
 */

public class GitYmlParaUtils {
	@Value("${repotrTime}")
	private  String repotrTime;
	
	@Value("${repotrTimeError}")
	private  String repotrTimeError;
	
	@Value("${modelpath}")
	private String modelpath;
	
	@Value("${tempmodel}")
	private String tempmodel;
	
	@Value("${downrepor}")
	private String downreport;
	
	
	public String getRepotrTime() {
		return repotrTime;
	}


	public void setRepotrTime(String repotrTime) {
		this.repotrTime = repotrTime;
	}


	public String getRepotrTimeError() {
		return repotrTimeError;
	}


	public void setRepotrTimeError(String repotrTimeError) {
		this.repotrTimeError = repotrTimeError;
	}

	public String getModelpath() {
		return modelpath;
	}


	public String getTempmodel() {
		return tempmodel;
	}


	public String getDownreport() {
		return downreport;
	}


	/**
	 * 处理归档的时间
	 * @param timeIndex 下标以0开始,表示取按";"之后的顺序
	 * @return
	 */
	public  Map<String,String> getSetTime(int timeIndex){
		
		Map<String,String> map = new HashMap<String, String>();
		
		String model = new DateTime().toString("YYYY-MM-dd");	 
		
		String[] times = repotrTime.split("\\;");
		
		for(int i=0;i<times.length;i++){
			
			if(i==timeIndex){
				
			 String	timeOne   ="T"+times[i];	
			 
			 DateTime startTimeModel = new DateTime(model+timeOne);
			 
			 String start = startTimeModel.toString("YYYY-MM-dd HH:mm:ss");
			 
			 String end = startTimeModel.plusMinutes(Integer.parseInt(repotrTimeError)).toString("YYYY-MM-dd HH:mm:ss");
				 
			 map.put("start", start);
			
			 map.put("end", end);
			}
		}
		
	  return map;
		
	}
}
