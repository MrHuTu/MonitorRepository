package com.zhongda.monitor.core.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhongda.monitor.account.model.User;
import com.zhongda.monitor.account.utils.ShiroUtils;
import com.zhongda.monitor.core.annotation.SysLogAnnotation;
import com.zhongda.monitor.core.exception.PlaceholderNotMatchParamException;
import com.zhongda.monitor.core.model.Result;
import com.zhongda.monitor.core.model.SysLog;
import com.zhongda.monitor.core.service.SysLogService;
import com.zhongda.monitor.core.utils.SpringUtils;

/**
 * 类SysLogAspect的功能描述:
 * 系统日志，切面处理类
 * @Author dengzm
 * @Date 2018年1月15日 下午5:06:24
 */
@Aspect
@Component(value="sysLogAspect")
public class SysLogAspect {
	
	private final static Logger logger = LoggerFactory.getLogger(SysLogAspect.class);
	
	private static SysLogService sysLogService;
	
	private static ObjectMapper objectMapper;
	
	@Pointcut("@annotation(com.zhongda.monitor.core.annotation.SysLogAnnotation)")
	public void logPointCut() {
		
	}
	
	@AfterReturning(value = "logPointCut()", returning = "retu")
	public void doAfterReturning(JoinPoint joinPoint, Object retu) {
		SysLog sysLog = getSysLog(joinPoint);
		// 异步保存日志
		new SaveSysLogThread(sysLog, retu).start();
    }
	
	@AfterThrowing(value = "logPointCut()", throwing = "ex")
	public void doAfterThrowing(JoinPoint joinPoint, Exception ex) {
		SysLog sysLog = getSysLog(joinPoint);
		// 异步保存日志
		new SaveSysLogThread(sysLog, ex).start();
    }
	
	/**
	 * 设置SysLog的属性
	 */
	private SysLog getSysLog(JoinPoint joinPoint){
		objectMapper = SpringUtils.getBean(ObjectMapper.class);
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		SysLog sysLog = new SysLog();
		
		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");
		
		//请求的参数
		Object[] args = joinPoint.getArgs();
		if(null != args && args.length > 0){
			//构建请求参数name-value的键值对
			Map<String, Object> nameValueMap = getArgsNameValueMap(className, methodName, args);
			String params = null;
			try {
				if(null == nameValueMap){
					params = objectMapper.writeValueAsString(args);
				} else {
					params = objectMapper.writeValueAsString(nameValueMap);
				}
			} catch (JsonProcessingException e) {
				logger.warn("方法参数name-value键值对转json格式字符串失败 ：" + e.getMessage());
				params = Arrays.toString(args);
			}
			sysLog.setParams(params);
		}
		
		//请求的操作
		SysLogAnnotation sysLogAnnotation = method.getAnnotation(SysLogAnnotation.class);
		if(sysLogAnnotation != null){
			//注解上的描述 
			String operation = sysLogAnnotation.value();
			//用相应的参数替换对应的占位符
			operation = replcaePlaceholder(args, operation);
			sysLog.setOperation(operation);
		}
		
		//设置用户参数
		User user = null;
		if(ShiroUtils.isLogin()){ //如果用户已登录
			user = ShiroUtils.getCurrentUser(); //获取当前登录的用户
		} else {
			user = ShiroUtils.getUnLoginUser(); //当前未登录，但包含用户信息
		}
		if(null == user){
			sysLog.setUserId(0);	
			sysLog.setUserName("当前操作用户未被获取");
		} else {
			sysLog.setUserId(user.getUserId());	
			sysLog.setUserName(user.getUserName());	
		}
		sysLog.setCreateDate(new Date());
		return sysLog;
	}
	
	/**
	 * 用相应的参数替换对应的占位符
	 * @param args 参数列表
	 * @param operation 包含占位符的字符串，占位符格式${1}
	 * @return 返回替换后的字符串
	 */
	private String replcaePlaceholder(Object[] args, String operation) {
		String[] placeholders = operation.split("\\$\\{");
		if(placeholders.length - 1 > args.length){
			throw new PlaceholderNotMatchParamException("SysLogAnnotation注解的占位符的数量超过参数的数量");
		} else {
			try {
				for (int i = 1; i < placeholders.length; i++) {
					String indexString = placeholders[i].substring(0, placeholders [i].indexOf("}"));
					int index = Integer.parseInt(indexString);
					if(index > args.length - 1){
						throw new PlaceholderNotMatchParamException("SysLogAnnotation注解的占位符的数字超过参数的数量");
					}
					String param = null;
					if(args[index].toString().contains("@")){
						try {
							param = objectMapper.writeValueAsString(args[index]);
						} catch (JsonProcessingException e) {
							logger.warn("引用类型参数转json格式字符串失败 ：" + e.getMessage());
							param = args[index].toString();
						}
					} else {
						param = args[index].toString();
					}
					operation = operation.replace("${" + index + "}", param);
				}
			} catch (Exception e) {
				throw new PlaceholderNotMatchParamException("SysLogAnnotation注解的占位符格式不正确或者与参数不匹配");
			}
		}
		return operation;
	}
	
	/**
	 * 获取方法的参数名和参数值
	 * 反射出方法的参数 - 通过高级反射javassist反射出方法
	 */
	private Map<String,Object> getArgsNameValueMap(String className, String methodName, Object[] args){   
		Map<String,Object > map = null;
		try {
	        Class<?> clazz = Class.forName(className);//获取目标对象的类对象
			String clazzName = clazz.getName();    
	        ClassPool pool = ClassPool.getDefault();//创建默认的ClassPool对象，该方法是单例的    
	        pool.insertClassPath(new ClassClassPath(this.getClass()));//设置从当前类目录下加载Class类    
	        CtClass cc = pool.get(clazzName);    
	        CtMethod cm = cc.getDeclaredMethod(methodName);    
	        MethodInfo methodInfo = cm.getMethodInfo();
	        int length = cm.getParameterTypes().length;
	        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();    
	        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);    
	        if (null != attr) {  
	        	map = new HashMap<String,Object>();
	        	int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;    
	            for (int i = 0; i < length; i++){    
	                map.put( attr.variableName(i + pos),args[i]);
	            }  
	        }    
		} catch (Exception e) {
			logger.warn("反射失败,不能获取方法的参数名,构建默认参数值：" + e.getMessage());
		}    
        return map;    
    }    
	
	/**
	 * 保存日志线程
	 */
	public static class SaveSysLogThread extends Thread{
		
		private SysLog sysLog;
		private Object descriptionInfo;
		
		public SaveSysLogThread(SysLog SysLog, Object descriptionInfo){
			super(SaveSysLogThread.class.getSimpleName());
			this.sysLog = SysLog;
			this.descriptionInfo = descriptionInfo;
		}
		
		@Override
		public void run() {
			sysLogService = SpringUtils.getBean(SysLogService.class);
			if(descriptionInfo instanceof Exception){
				Exception ex = (Exception) descriptionInfo;
				StackTraceElement[] st = ex.getStackTrace();
				String exclass = st[0].getClassName() + st[0].getMethodName();
				sysLog.setDescription("出现异常->异常代码:" + exclass + "," + "异常信息:" + ex.getMessage());
			}else if(descriptionInfo instanceof Result){
				Result<?> result = (Result<?>) descriptionInfo;
				String msg = result.getCode() == Result.SUCCESS ? "操作成功" : "操作失败";
				sysLog.setDescription(msg + ":" + result.getMsg());
			}else if(null != descriptionInfo){
				String description = descriptionInfo.toString();
				if(description.contains("@")){
					String returnInfo = null;
					try {
						returnInfo = objectMapper.writeValueAsString(descriptionInfo);
					} catch (JsonProcessingException e) {
						logger.warn("引用类型参数转json格式字符串失败 ：" + e.getMessage());
						returnInfo = description;
					}
					sysLog.setDescription("操作成功,返回值是" + returnInfo);
				} else if(description.equalsIgnoreCase("true") || description.equalsIgnoreCase("success") || description.equalsIgnoreCase("ok")){
					sysLog.setDescription("操作成功,返回值是" + description);
				} else {
					sysLog.setDescription("操作失败,返回值是" + description);
				}
			}else{
				sysLog.setDescription("无返回值,无法确定操作是否成功");
			}
			// 保存日志信息
			sysLogService.insertSysLog(sysLog);
		}
	}
}
