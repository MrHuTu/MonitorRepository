package com.zhongda.monitor.report.task;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zhongda.monitor.report.service.impl.WordUtil2007ServiceImpl;
import com.zhongda.monitor.report.utils.GitYmlParaUtils;


@Component
public class ClearSystem {
	
	private  Logger logger = LoggerFactory.getLogger(ClearSystem.class);
	
	@Autowired
	GitYmlParaUtils gtYmlParaUtils;
	
	/**
	 * 定时清理缓存的报告文件
	 */
	@Scheduled( fixedRate = 1000*60*3)
	private void clearFile(){
		
		String directoryName = gtYmlParaUtils.accordingOsGetParm("temp");
		
		File file =  new File(directoryName);
		
		if(file.isDirectory()){
			
		   String[] list =  file.list();
		   
		   for(int i=0;i<list.length;i++){
			   
			   File fileTemp = new File(directoryName,list[i]);
			   
			  boolean succeed = fileTemp.delete();
			  
			  if(succeed){
				  
				  logger.info(file+";目录下成功删除文件:"+list[i]);
				  
			  }
			   
			   
			   
		   }
		   
			
		}
		
	}

}
