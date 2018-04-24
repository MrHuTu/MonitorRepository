package com.zhongda.monitor.report.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 * @author 胡超  2018年4月24日10:29:59
 *
 */
public class CopyFileUtils {
	/**
     * 复制文件
     * @param fromFile
     * @param toFile
     * 
     * 
     * @throws IOException 
     */   
    public static void copyFile(String fromFile,String toFile){
    	
    	File insFile = new File(fromFile);
   	   	
    	File outFile = new File(toFile);
    	
        FileInputStream ins;
		try {
			
			ins = new FileInputStream(insFile);
			
			 FileOutputStream out = new FileOutputStream(outFile);
		        
		        byte[] b = new byte[1024];
		        
		        int n=0;
		        
		        while((n=ins.read(b))!=-1){
		            out.write(b, 0, n);
		        }
		        
		        ins.close();
		        
		        out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       
    }
}
