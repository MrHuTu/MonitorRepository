package com.zhongda.monitor.report.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 边坡类的数据头实体
 * @author hu.chao
 *
 */
public class SideTableDataModel {
	
	 private String lineOne ="工程名称：广西贵港至隆安高速公路边坡在线安全监测报告";
	 
	 private String lineTwo = "测试单位:中大检测";
	 
	 private String lineThreeCellone="初次测试时间:2018-01-28 00:00:00";
	 private String lineThreeCelltwo="测试人:中大监测云平台";
	 
	 private String lineFourCellone = "传感器编号:16";
	 private String lineFourCellTwo = "测点名称:SPWY16";
	 
	 private String lineFiveCellOne = "终端编号(DTU):2017100008";
	 private String lineFiveCellTwo = "采集器通道:00";
	 
	 
	public String getLineOne() {
		return lineOne;
	}
	public void setLineOne(String lineOne) {
		this.lineOne = lineOne;
	}
	public String getLineTwo() {
		return lineTwo;
	}
	public void setLineTwo(String lineTwo) {
		this.lineTwo = lineTwo;
	}
	public String getLineThreeCellone() {
		return lineThreeCellone;
	}
	public void setLineThreeCellone(String lineThreeCellone) {
		System.out.println("setLineThreeCellone:"+lineThreeCellone);
		
		this.lineThreeCellone = lineThreeCellone;
	}
	public String getLineThreeCelltwo() {
		return lineThreeCelltwo;
	}
	public void setLineThreeCelltwo(String lineThreeCelltwo) {
		System.out.println("1setLineThreeCelltwo:"+lineThreeCelltwo);
		this.lineThreeCelltwo = lineThreeCelltwo;
		System.out.println("2setLineThreeCelltwo:"+this.lineThreeCelltwo);
	}
	public String getLineFourCellone() {
		return lineFourCellone;
	}
	public void setLineFourCellone(String lineFourCellone) {
		
		this.lineFourCellone = lineFourCellone;
	}
	public String getLineFourCellTwo() {
		return lineFourCellTwo;
	}
	public void setLineFourCellTwo(String lineFourCellTwo) {
		System.out.println("setLineFourCellTwo:"+lineFourCellTwo);
		this.lineFourCellTwo = lineFourCellTwo;
	}
	public String getLineFiveCellOne() {
		return lineFiveCellOne;
	}
	public void setLineFiveCellOne(String lineFiveCellOne) {
		System.out.println("setLineFiveCellOne:"+lineFiveCellOne);
		this.lineFiveCellOne = lineFiveCellOne;
	}
	public String getLineFiveCellTwo() {
		return lineFiveCellTwo;
	}
	public void setLineFiveCellTwo(String lineFiveCellTwo) {
		System.out.println("setLineFiveCellTwo:"+lineFiveCellTwo);
		this.lineFiveCellTwo = lineFiveCellTwo;
	}
	
	
	 @Override
	public String toString() {
		return "SideTableHeadModel [lineOne=" + lineOne + ", lineTwo="
				+ lineTwo + ", lineThreeCellone=" + lineThreeCellone
				+ ", lineThreeCelltwo=" + getLineThreeCelltwo()
				+ ", lineFourCellone=" + lineFourCellone + ", lineFourCellTwo="
				+ lineFourCellTwo + ", lineFiveCellOne=" + lineFiveCellOne
				+ ", lineFiveCellTwo=" + lineFiveCellTwo + "]";
	}
	 /**
	  * 这个方法是用来统一表头内容长度的，和模拟居中的，防止表格变形的作用。注释部分如要删除请联系研发
	  */
	public void unifyLength(){		 		
		 String local = " ";
		 String maxlocal="      ";
		 String headLocal = "                   ";
		/*Map<String,String> map = new  HashMap<String, String>();
		
		map.put("LineThreeCellone", this.lineThreeCellone);
		
		map.put("LineThreeCelltwo", this.lineThreeCelltwo);
		
		map.put("LineFourCellone", this.lineFourCellone);
		
		map.put("LineFourCellTwo", this.lineFourCellTwo);
		
		map.put("LineFiveCellOne", this.lineFiveCellOne);
		
		map.put("LineFiveCellTwo", this.lineFiveCellTwo);
		
		int max = 0;
		String max_key= null;
		String max_value= null;
		Iterator<String> ite = map.keySet().iterator();
		
		while(ite.hasNext()){
			
			String key = ite.next();
			
			String value = map.get(key);
			
			if(max<value.getBytes().length){
				
				max = value.getBytes().length;
				 max_key= key;
				 max_value= value;
			}
			
		}
		System.out.println(max);
		
		Iterator<String> ite1 = map.keySet().iterator();
		
		Map<String,String> mapTemp = new  HashMap<String, String>();
		
		while(ite1.hasNext()){
			
			String key = ite1.next();
			
			String value = map.get(key);
			
			int count = max -value.getBytes().length;
			
			if(count>0){
				
				for(int j=1;j<=count;j++){
					value=local+value;
				}
				
				mapTemp.put(key, value);
			}
			
			
		}
		
		for(Map.Entry<String, String> v: mapTemp.entrySet()){
			String key = v.getKey();
			String value = v.getValue();
			callMethod(key,value);
		}
		callMethod(max_key,maxlocal+max_value);*/
		
		callMethod("LineOne",headLocal+this.getLineOne());
		 String temp = "";
		for(int i=0;i<45;i++){
			temp+=local;
		}
		callMethod("LineTwo",temp+this.getLineTwo());
		
	 }
	 
	 private void callMethod(String method,String value){
		// String className = "com.zhongda.monitor.business.wordreport.model.SideTableDataModel";
		  String methodName = "set"+method;
		  @SuppressWarnings("rawtypes")
		Class clz = null;
		try {
			clz = this.getClass();
			 //  
			 // Object obj = clz.newInstance();
			  //获取方法  
			  Method m = this.getClass().getDeclaredMethod(methodName, String.class);
			  //调用方法  
			  @SuppressWarnings("unused")
			String  result = (String) m.invoke(this, value);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	 
}
