package com.zhongda.monitor.account.aspect;

import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zhongda.monitor.account.annotation.IgnoreSecurity;
import com.zhongda.monitor.account.exception.TokenException;
import com.zhongda.monitor.account.security.Token;
import com.zhongda.monitor.account.service.TokenService;

/**
 * Title:安全检查切面(是否登录检查) 
 * Description: 通过验证Token维持登录状态
 * @Author dengzm
 * @Date 2018年1月11日 下午4:04:25
 */
//@Aspect
//@Component("securityAspect")
public class SecurityAspect {

	private static final Logger logger = Logger.getLogger(SecurityAspect.class);
	
	@Resource
	private TokenService tokenService;
	
	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public Object execute(ProceedingJoinPoint pjp) throws Throwable {
		// 从切点上获取目标方法
		MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
		logger.debug("methodSignature : " + methodSignature);
		Method method = methodSignature.getMethod();
		logger.debug("Method : " + method.getName() + " : "
				+ method.isAnnotationPresent(IgnoreSecurity.class));
		// 若目标方法忽略了安全性检查,则直接调用目标方法
		if (method.isAnnotationPresent(IgnoreSecurity.class)) {
			return pjp.proceed();
		}
		
		Class<?> controllerClass = method.getDeclaringClass();
		RequestMapping requestMapping = controllerClass.getAnnotation(RequestMapping.class);
		if(requestMapping != null){
			//注解上的描述 
			String[] values = requestMapping.value();
			if(null != values && values.length == 1 && values[0].equals("/swagger-resources")){
				return pjp.proceed();
			}
		}
		
		// 从 request header 中获取当前 token
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		String token =request.getHeader(Token.DEFAULT_TOKEN_NAME);
		// 检查 token 有效性
		if (!tokenService.checkToken(token)) {
			String message = String.format("token [%s] 已经失效", token);
			logger.debug("message : " + message);
			throw new TokenException(message);
		}
		// 调用目标方法
		return pjp.proceed();
	}
}
