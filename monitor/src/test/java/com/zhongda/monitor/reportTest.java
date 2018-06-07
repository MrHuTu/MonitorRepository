package com.zhongda.monitor;

import java.util.Calendar;
import java.util.Date;

public class reportTest {

	public static void main(String[] args) {
			
		
		Date date = new Date();
		  Calendar cal = Calendar.getInstance();  
		  
		  cal.setTime(date);
	
		
		 // cal.setFirstDayOfWeek(Calendar.MONDAY);
		  int dayWeek = cal.get(Calendar.DAY_OF_WEEK)-1;// 获得当前日期是一个星期的第几天  
		  if(dayWeek==0) dayWeek=7;
		  System.out.println("当前星期的第"+dayWeek+"天");
	}
		

}
