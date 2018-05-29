package com.zhongda.monitor.report.configclass;


/**
 * 生成表格的配javaBean,表格样式类全名（该配置类必须以createTable方法为入口,即必须实现表格样式标准接口BastTableClass）+"|"+CreateTableConfig下的自定义方法（该方法必须返回CreateTableConfig类对象）
 * @author huchao
 *
 */
public class ReportConfig {//Sedimentation  Convergence public static final  String SEDIMENTATION ="com.zhongda.monitor.report.configclass.tableclass.SedimentationTableClass|getSideTable";
	/*
	 * 日报
	 */
	
	//沉降
	public static final  String DAY_SEDIMENTATION ="com.zhongda.monitor.report.configclass.tableclass.dayreport.SedimentationTableClassForDay";
	//水平位移
	public static final  String DAY_COVERGENCE =DAY_SEDIMENTATION;
	/**
	 * 周报
	 */
	//沉降,水平位移
	public static final  String WEEK_SEDIMENTATION ="com.zhongda.monitor.report.configclass.tableclass.weekreport.SedimentationTableClassForWeek";
}
