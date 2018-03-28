package com.zhongda.monitor.core.config;

import javax.servlet.DispatcherType;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.zhongda.monitor.business.filter.GzipFilter;
import com.zhongda.monitor.core.filter.CorsFilter;

@Configuration
public class FilterConfig {

	/**
	 * 注册CorsFilter
	 */
	@Bean
	public FilterRegistrationBean corsFilterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		CorsFilter corsFilter = new CorsFilter();
		filterRegistrationBean.setFilter(corsFilter);
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.setName("corsFilter");
		filterRegistrationBean.setOrder(1);
		return filterRegistrationBean;
	}

	/**
	 * 注册ShiroFilter DelegatingFilterProxy通过代理模式将Spring容器中的bean和filter关联起来
	 */
	@Bean
	public FilterRegistrationBean shiroFilterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
		// 设置targetFilterLifecycle为true, 由servlet控制filter生命周期
		delegatingFilterProxy.setTargetFilterLifecycle(true);
		delegatingFilterProxy.setTargetBeanName("shiroFilter");
		filterRegistrationBean.setFilter(delegatingFilterProxy);
		filterRegistrationBean.setEnabled(true);
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST);
		filterRegistrationBean.setOrder(2);
		return filterRegistrationBean;
	}

	/**
	 * 注册GzipFilter
	 */
	@Bean
	public FilterRegistrationBean gzipFilterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		GzipFilter gzipFilter = new GzipFilter();
		filterRegistrationBean.setFilter(gzipFilter);
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.setName("gzipFilter");
		filterRegistrationBean.setOrder(1);
		return filterRegistrationBean;
	}
}
