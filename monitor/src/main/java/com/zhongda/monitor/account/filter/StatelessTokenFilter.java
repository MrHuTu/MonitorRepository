package com.zhongda.monitor.account.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhongda.monitor.account.security.StatelessToken;
import com.zhongda.monitor.core.model.Result;

/**
 * Title: 跨域访问处理(跨域资源共享) 
 * Description: 解决前后端分离架构中的跨域问题
 * @Author dengzm
 * @Date 2018年1月16日 上午9:11:42
 */
public class StatelessTokenFilter extends AccessControlFilter {

	private static final Logger logger = LoggerFactory.getLogger(StatelessTokenFilter.class);
	
	/**
     * CROS复杂请求时会先发送一个OPTIONS请求，来测试服务器是否支持本次请求，这个请求时不带数据的，请求成功后才会发送真实的请求。
     * 所以第一次发送请求要确认服务器支不支持接收这个header。第二次才会传数据，所以我们要做的就是把所有的OPTIONS请求统统放行
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;
        if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpResponse.setHeader("Access-control-Allow-Origin", "*");
            httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            httpResponse.setHeader("Access-Control-Allow-Headers", httpRequest.getHeader("Access-Control-Request-Headers"));
            httpResponse.setHeader("Access-Control-Expose-Headers", httpRequest.getHeader("Access-Control-Expose-Headers"));
            httpResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
	
    /**
     * 先执行：isAccessAllowed 再执行onAccessDenied
     * isAccessAllowed：表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，
     * 如果允许访问返回true，否则false；
     * 如果返回true的话，就直接返回交给下一个filter进行处理。
     * 如果返回false的话，回往下执行onAccessDenied
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        //处理需要过滤的请求
    	if(null != mappedValue){
    		logger.info(mappedValue.toString());
    	}
        return false;
    }
	
    /**
     * onAccessDenied：表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；
     * 如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     */
	@Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest req = (HttpServletRequest) request;
		String authorization = req.getHeader(StatelessToken.DEFAULT_TOKEN_NAME);
		if (null != authorization && authorization.startsWith(StatelessToken.TOKEN_HEADER_PREFIX)){
			//截取token得到jwt格式的token信息
            String tokenString = authorization.substring(StatelessToken.TOKEN_HEADER_PREFIX.length() + 1);
            //2、生成无状态Token
            StatelessToken statelessToken = new StatelessToken(tokenString);
            try {
                //3、委托给Realm进行登录
                super.getSubject(request, response).login(statelessToken);
            } catch (Exception e) {
                //4、登录失败
                onLoginFail(response, "认证失败,token无效或已失效，请重试或者重新登录！");
                return false;
            }
            return true;
		}
		this.onLoginFail(response, "无权访问，请先登录！");
        return false;  
	}  
	
	//登录失败时默认返回401状态码
    private void onLoginFail(ServletResponse response, String msg) throws IOException {
    	Result<String> result = new Result<String>();
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setCharacterEncoding("utf-8");
        result.setCode(Result.FAILURE).setMsg(msg);
    	ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(result);
        httpResponse.getWriter().write(jsonString);
        httpResponse.getWriter().flush();
    }
}