package com.zhongda.monitor.report.utils;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("GitYmlParaUtils")
/**
 * 取yml配置文件参数
 * @author 胡超
 *
 */

public class GitYmlParaUtils {
	
	private  Logger logger = LoggerFactory.getLogger(GitYmlParaUtils.class);
	
	@Value("${repotrTime}")
	private  String repotrTime;
	
	@Value("${repotrTimeError}")
	private  String repotrTimeError;
	//windox 下
	@Value("${modelpath}")
	private String modelpath;
		
	@Value("${downrepor}")
	private String downreport;
	//linux下
	@Value("${linuxmodelpath}")
	private String linuxmodelpath;
	
	@Value("${linuxdownrepor}")
	private String linuxdownrepor;
	
	@Value("${linuxuploadfiles}")
	private String linuxuploadfiles;
	
	
	@Value("${uploadfiles}")
	private String uploadfiles;
	
	@Value("${pictype}")
	
	private String pictype;
	
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


	public String getDownreport() {
		return downreport;
	}


	public String getLinuxmodelpath() {
		return linuxmodelpath;
	}


	public String getLinuxdownrepor() {
		return linuxdownrepor;
	}


	public String getLinuxuploadfiles() {
		return linuxuploadfiles;
	}


	public String getUploadfiles() {
		return uploadfiles;
	}


	public String getPictype() {
		return pictype;
	}


	/**
	 * 处理归档的时间
	 * @param timeIndex 下标以0开始,表示取按";"之后的顺序
	 * @return
	 */
	public   synchronized  Map<String,String> getSetTime(int timeIndex){
		
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
	/**
	 * 根据os获取对应参数
	 */
	
	public String accordingOsGetParm(String parm){
		
		String tageParm = null;
		
		String osName = System.getProperty("os.name", "");  
		
		if(osName.startsWith("Windows")){
			
			logger.info("进入Windows模板路径");
			
			if(parm.equals("path")){
				
				tageParm = modelpath;
				
			}else if(parm.equals("temp")){
				
				tageParm = downreport;
				
			}else if(parm.equals("upload")){
				
				tageParm=uploadfiles;
				
			}									
			
		} else {
			
				if(parm.equals("path")){
					
					tageParm = linuxmodelpath;
							
				}else if(parm.equals("temp")){
					
					tageParm = linuxdownrepor;
					
				}else if(parm.equals("upload")){
					
					tageParm=linuxuploadfiles;
					
				}					
				
		}
		
		return tageParm;
		
		
		
	}
}
