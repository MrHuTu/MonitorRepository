package com.zhongda.monitor.core.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
  
/**
 * Title: 跨域访问处理(跨域资源共享)    
 * Description: 解决前后端分离架构中的跨域问题
 * @Author dengzm
 * @Date 2018年1月16日 上午9:11:42
 */
public class CorsFilter implements Filter {

	/** Log4j日志处理 */
	private static final Logger logger = Logger.getLogger(CorsFilter.class);
	
	private String allowOrigin;
	private String allowMethods;
	private String allowCredentials;
	private String allowHeaders;
	private String exposeHeaders;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		allowOrigin = filterConfig.getInitParameter("allowOrigin");
		allowMethods = filterConfig.getInitParameter("allowMethods");
		allowCredentials = filterConfig.getInitParameter("allowCredentials");
		allowHeaders = filterConfig.getInitParameter("allowHeaders");
		exposeHeaders = filterConfig.getInitParameter("exposeHeaders");
	}

	  
	/**
	 *  通过CORS技术实现AJAX跨域访问,只要将CORS响应头写入response对象中即可
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String currentOrigin = request.getHeader("Origin");
		logger.debug("currentOrigin : " + currentOrigin);
		if (null != allowOrigin && allowOrigin.length() != 0) {
			List<String> allowOriginList = Arrays
					.asList(allowOrigin.split(","));
			logger.debug("allowOriginList : " + allowOrigin);
			if (null != allowOriginList && allowOriginList.size() != 0) {
				if (allowOriginList.contains(currentOrigin)) {
					response.setHeader("Access-Control-Allow-Origin",
							currentOrigin);
				}
			}
			if(null == response.getHeader("Access-Control-Allow-Origin")){
				logger.debug("currentOrigin is null, set Access-Control-Allow-Origin=*");
				response.setHeader("Access-Control-Allow-Origin","*");
			}
		}
		if (null != allowMethods && allowMethods.length() != 0) {
			response.setHeader("Access-Control-Allow-Methods", allowMethods);
		}
		if (null != allowCredentials && allowCredentials.length() != 0) {
			response.setHeader("Access-Control-Allow-Credentials",
					allowCredentials);
		}
		if (null != allowHeaders && allowHeaders.length() != 0) {
			response.setHeader("Access-Control-Allow-Headers", allowHeaders);
		}
		if (null != exposeHeaders && exposeHeaders.length() != 0) {
			response.setHeader("Access-Control-Expose-Headers", exposeHeaders);
		}
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
	}
}