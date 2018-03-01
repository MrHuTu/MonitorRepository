package com.zhongda.monitor.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
  
/**
 * Title: 跨域访问处理(跨域资源共享)    
 * Description: 解决前后端分离架构中的跨域问题
 * @Author dengzm
 * @Date 2018年1月16日 上午9:11:42
 */
public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse)res;
        response.setHeader("Access-Control-Allow-Origin", "*");// 允许所有域进行访问,可以指定多个Access-Control-Allow-Origin:http://localhost:8080/
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");// 允许的方法
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Cache-Control, Authorization, username");
        //response.setHeader("Access-Control-Expose-Headers", "content-type" + ", " + Constant.EXPOSE_HEADER);//如果不设置，js获取不到头信息
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }
}