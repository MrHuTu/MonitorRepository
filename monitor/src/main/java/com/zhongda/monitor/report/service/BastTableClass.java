package com.zhongda.monitor.report.service;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlCursor;

import com.zhongda.monitor.report.configclass.configmodel.CreateTableConfig;




/**
 * 表格样式标准接口
 * @author 胡超  2018年4月19日15:39:18
 *
 */
public interface BastTableClass {
	/**
	 * 
	 * @param config
	 */
	public  void createTable(CreateTableConfig config);
	
	/**
	 * 边坡类型的配置参数     沉降  和收敛的配置
	 * @param doc2
	 * @param cursor
	 * @param Object 填充到表格的数据
	 * @return
	 */
	public  CreateTableConfig  getTableClassConfig(XWPFDocument doc2,XmlCursor cursor,Object obj);
}
