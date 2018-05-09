package com.zhongda.monitor.report.configclass.filldata;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 * 填充对应表格要实现的接口
 * @author 胡超 2018年4月18日15:14:13
 *
 */
public interface FillBasics {
	
	public void fillData(XWPFDocument doc2,String pojoId,String time);
	
}
