package com.zhongda.monitor.report.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import com.zhongda.monitor.report.service.ProjectParaService;
import com.zhongda.monitor.report.service.ReportConfigService;
import com.zhongda.monitor.report.service.ReportParaService;
import com.zhongda.monitor.report.utils.ReportConfigOpUtils;

/**
 * 服务启动的时候加载报表配置
 * 
 * @author 胡超 2018年4月17日11:21:01
 *
 */
@Configuration
public class BeforeStartup implements ApplicationListener<ContextRefreshedEvent> {
	private static final Logger logger = LoggerFactory.getLogger(BeforeStartup.class);
	@Autowired
	private ReportParaService reportParaService;
	
	@Autowired
	private ReportConfigService reportConfigService;
	
	@Autowired
	private ProjectParaService projectParaService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		
		ReportConfigOpUtils.reportConfigs =	reportConfigService.selectReportConfig();
		logger.info("========================================================报告开关参数加载成功=========================================================================================");
		ReportConfigOpUtils.reportParas = reportParaService.selectReportPara();
		
		logger.info("========================================================报告配置参数加载成功=========================================================================================");
		
		ReportConfigOpUtils.projectPara = projectParaService.selectProjectPara();
		
		logger.info("========================================================项目下的全部在线监测参数加载成功==================================================================================");
	}

}
