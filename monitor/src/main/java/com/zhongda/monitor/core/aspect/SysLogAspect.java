package com.zhongda.monitor.core.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhongda.monitor.account.model.User;
import com.zhongda.monitor.account.utils.ShiroUtils;
import com.zhongda.monitor.core.annotation.SysLogAnnotation;
import com.zhongda.monitor.core.model.SysLog;
import com.zhongda.monitor.core.service.SysLogService;

/**
 * 类SysLogAspect的功能描述:
 * 系统日志，切面处理类
 * @Author dengzm
 * @Date 2018年1月15日 下午5:06:24
 */
@Aspect
@Component(value="sysLogAspect")
public class SysLogAspect {
	
	@Resource
	private SysLogService sysLogService;
	
	@Resource
	private ObjectMapper objectMapper;
	
	@Pointcut("@annotation(com.zhongda.monitor.core.annotation.SysLogAnnotation)")
	public void logPointCut() { 
		
	}
	
	@Before("logPointCut()")
	public void saveSysLog(JoinPoint joinPoint) throws JsonProcessingException {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		
		SysLog sysLog = new SysLog();
		SysLogAnnotation sysLogAnnotation = method.getAnnotation(SysLogAnnotation.class);
		if(sysLogAnnotation != null){
			//注解上的描述 
			sysLog.setOperation(sysLogAnnotation.value());
		}
		
		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");
		
		//请求的参数
		Object[] args = joinPoint.getArgs();
		if(args.length > 0){
			String params = objectMapper.writeValueAsString(args[0]);
			sysLog.setParams(params);
		}
		
		//用户名
		User user = ShiroUtils.getUser();
		
		sysLog.setUserId(user.getUserId());
		sysLog.setUserName(user.getUserName());
		
		sysLog.setCreateDate(new Date());
		//保存系统日志
		sysLogService.insertSysLog(sysLog);
	}
	
}
