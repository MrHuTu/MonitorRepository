package com.zhongda.monitor.report.model.fictitious;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.zhongda.monitor.report.model.ReportData;

/**
 * 边坡类的数据头实体
 * @author hu.chao
 *
 */
public class SideTableData extends BasicsModel{
	
	// private String typ ;
	 private String lineOne ="工程名称：广西贵港至隆安高速公路边坡在线安全监测报告";
	 
	 private String lineTwo = "测试单位:中大检测";
	 
	 private String lineThreeCellone="初次测试时间:2018-01-28 00:00:00";
	 private String lineThreeCelltwo="测试人:中大监测云平台";
	 
	 private String lineFourCellone = "传感器编号:16";
	 private String lineFourCellTwo = "测点名称:SPWY16";
	 
	 private String lineFiveCellOne = "终端编号(DTU):2017100008";
	 private String lineFiveCellTwo = "采集器通道:00";
	 
	 private List<ReportData> reportDatas;
	 
	 
	public String getLineOne() {
		
		if(lineOne.indexOf( "工程名称:")!=-1){
			
			return lineOne;
			
		}else{
			return "工程名称:"+lineOne;
		}
		
		
	}
	public void setLineOne(String lineOne) {
		
		this.lineOne = lineOne;
	}
	public String getLineTwo() {
		
		if(lineTwo.indexOf("测试单位:")!=-1){
			
			return lineTwo;
			
		}else{
			
			return "测试单位:"+lineTwo;
		}
	
	}
	public void setLineTwo(String lineTwo) {
		
		this.lineTwo = lineTwo;
	}
	public String getLineThreeCellone() {
		
		if(lineThreeCellone.indexOf("初次测试时间:")!=-1){
			
			return  lineThreeCellone;
		}else{
			
			return "初次测试时间:"+lineThreeCellone;
		}
		
		
	}
	public void setLineThreeCellone(String lineThreeCellone) {
	
		
		this.lineThreeCellone = lineThreeCellone;
	}
	public String getLineThreeCelltwo() {
		
		if(lineThreeCelltwo.indexOf("测试人:")!=-1){
			
			return lineThreeCelltwo;
			
		}else{
			
			return lineThreeCelltwo;
		}
		
	}
	public void setLineThreeCelltwo(String lineThreeCelltwo) {
		
		this.lineThreeCelltwo = lineThreeCelltwo;
		
	}
	public String getLineFourCellone() {
		if(lineFourCellone.indexOf("传感器编号:")!=-1){
			
			return lineFourCellone;
		}else{
			return "传感器编号:"+lineFourCellone;
		}
		
	}
	public void setLineFourCellone(String lineFourCellone) {
		
		this.lineFourCellone = lineFourCellone;
	}
	public String getLineFourCellTwo() {
		
		if(lineFourCellTwo.indexOf("测点名称:")!=-1){
			
			return lineFourCellTwo;
			
		}else{
			return "测点名称:"+lineFourCellTwo;
		}
		
		
	}
	public void setLineFourCellTwo(String lineFourCellTwo) {
	
		this.lineFourCellTwo = lineFourCellTwo;
	}
	public String getLineFiveCellOne() {
		
		if(lineFiveCellOne.indexOf("终端编号(DTU)")!=-1){
			
			return "终端编号(DTU):"+lineFiveCellOne;
			
		}else{
			return lineFiveCellOne;
		}
		
		
	}
	public void setLineFiveCellOne(String lineFiveCellOne) {
		
		this.lineFiveCellOne = lineFiveCellOne;
	}
	public String getLineFiveCellTwo() {
		
		if(lineFiveCellTwo.indexOf("采集器通道:")!=-1){
			
			return lineFiveCellTwo;
			
		}else{
			
			return "采集器通道:"+lineFiveCellTwo;
		}
		
		
	}
	public void setLineFiveCellTwo(String lineFiveCellTwo) {
	
		this.lineFiveCellTwo = lineFiveCellTwo;
	}
	
	public List<ReportData> getReportDatas() {
		return reportDatas;
	}
	public void setReportDatas(List<ReportData> reportDatas) {
		this.reportDatas = reportDatas;
	}
	/*public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}*/
	
	@Override
	public String toString() {
		return "SideTableData [typ="+super.getTyp() + ", lineOne=" + lineOne
				+ ", lineTwo=" + lineTwo + ", lineThreeCellone="
				+ lineThreeCellone + ", lineThreeCelltwo=" + lineThreeCelltwo
				+ ", lineFourCellone=" + lineFourCellone + ", lineFourCellTwo="
				+ lineFourCellTwo + ", lineFiveCellOne=" + lineFiveCellOne
				+ ", lineFiveCellTwo=" + lineFiveCellTwo + ", ReportDatas="
				+ reportDatas + "]";
	}
	/**
	  * 这个方法是用来统一表头内容长度的，和模拟居中的，防止表格变形的作用。注释部分如要删除请联系研发
	  */
	public void unifyLength(){		 		
		 String local = " ";
		 String headLocal = "                   ";		
		callMethod("LineOne",headLocal+this.getLineOne());
		 String temp = "";
		for(int i=0;i<25;i++){
			temp+=local;
		}
		callMethod("LineTwo",temp+this.getLineTwo());
		
	 }
	 
	 private void callMethod(String method,String value){
	
		  String methodName = "set"+method;
		  @SuppressWarnings({ "rawtypes", "unused" })
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
			
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			
			e.printStackTrace();
		} catch (SecurityException e) {
			
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			
			e.printStackTrace();
		}
		 
	 }
	 
}
