package com.zhongda.monitor.report.model;

/**
 * 验证用户请求的项目下的参数是否支持生成word报告
 * 
 * @author 胡超
 *
 */
public class ReportPara {
	private String parameter;

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	@Override
	public String toString() {
		return "ReportPara [parameter=" + parameter + "]";
	}

}
