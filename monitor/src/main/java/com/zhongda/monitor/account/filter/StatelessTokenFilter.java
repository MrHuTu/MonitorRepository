package com.zhongda.monitor.account.filter;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhongda.monitor.account.security.StatelessToken;
import com.zhongda.monitor.account.service.TokenService;
import com.zhongda.monitor.account.service.impl.TokenServiceImpl;

/**
 * Title: 跨域访问处理(跨域资源共享) 
 * Description: 解决前后端分离架构中的跨域问题
 * @Author dengzm
 * @Date 2018年1月16日 上午9:11:42
 */
public class StatelessTokenFilter extends AccessControlFilter {

	private final Logger logger = LoggerFactory.getLogger(StatelessTokenFilter.class);
	
	@Resource
	private TokenService tokenService = new TokenServiceImpl();
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		return false;
	}
	
	@Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

		logger.info("StatelessTokenFilter onAccessDenied");
		HttpServletRequest req = (HttpServletRequest) request;
		String authorization = req.getHeader(StatelessToken.HEADER);
		String password = req.getParameter("password");
		String userName = req.getParameter("userName");
		StatelessToken token = new StatelessToken(userName, password, authorization);
		System.out.println(token.getToken());
	    try {  
	    	//5、委托给Realm进行登录  
	    	getSubject(request, response).login(token);  
	    } catch (Exception e) {  
	    	e.printStackTrace();  
	    	onLoginFail(response); //6、登录失败  
	    	return false;  
	    }  
	    return true;  
	}  
	
	//登录失败时默认返回401状态码  
	private void onLoginFail(ServletResponse response) throws IOException {  
	    HttpServletResponse httpResponse = (HttpServletResponse) response;  
	    httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  
	    httpResponse.getWriter().write("login error");  
	}  

}