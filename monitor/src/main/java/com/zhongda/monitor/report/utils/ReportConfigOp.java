package com.zhongda.monitor.report.utils;

import java.util.Iterator;
import java.util.List;

import com.zhongda.monitor.report.model.ReportConfig;
import com.zhongda.monitor.report.model.ReportPara;

public class ReportConfigOp {
	public static List<ReportConfig> reportConfigs;
	public static List<ReportPara> reportParas;
	/**
	 * 验证项目报表开关是否开启
	 * @param projectId
	 * @return true 开启;false 关闭 不可用
	 */
	public boolean verifyreportConfig(String projectId) {

		Iterator<ReportConfig> paras = reportConfigs.iterator();
		while (paras.hasNext()) {
			ReportConfig reportConfig = paras.next();
			if(reportConfig.getProject_id()==Integer.parseInt(projectId)){
				if(reportConfig.getReportConfig_switch()==1){
					return true;
				}else{
					return false;
				}
			}
			

		}
		return false;
	}
	

	/**
	 * 验证 监测参数是否能支持生成报告
	 * 
	 * @param para
	 * @return
	 */
	public boolean verifyReportPara(String para) {

		Iterator<ReportPara> paras = reportParas.iterator();
		while (paras.hasNext()) {
			ReportPara reportPara = paras.next();
			if (para.equalsIgnoreCase(reportPara.getParameter())) {
				return true;
			}

		}
		return false;
	}
}
