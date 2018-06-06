package com.zhongda.monitor.report.model.fictitious;
/**
 * 所有数据模型(日报)bean必须继承的javaBean
 * 这个typ，对应report_data表中的typ,必须被差出来
 * @author 胡超
 *
 */
public class BasicsModel {
	
	private  String typ ;

	public String getTyp() {
		return typ;
	}
	
	public void unifyLength(){
		
	}
}
