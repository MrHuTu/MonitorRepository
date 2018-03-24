package com.zhongda.monitor.core.aspect;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.zhongda.monitor.account.exception.ForbiddenException;
import com.zhongda.monitor.business.exception.NoWeatherException;
import com.zhongda.monitor.core.exception.VaildCodeExpireException;
import com.zhongda.monitor.core.model.Result;

/**
 * 全局异常处理切面 Description: 利用 @ControllerAdvice + @ExceptionHandler
 * 组合处理Controller层RuntimeException异常
 * @Author dengzm
 * @Date 2018年1月11日 下午3:55:14
 */
@RestControllerAdvice   // 控制器增强
public class ExceptionAspect {

	private static final Logger logger = Logger.getLogger(ExceptionAspect.class);

	/**
	 * 400 - Bad Request。处理 HttpMessageNotReadableException 异常
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Result<String> handleHttpMessageNotReadableException(
			HttpMessageNotReadableException e) {
		logger.error("请求参数不能转换...->" + e.getMessage());
		return new Result<String>().failure("请求参数不能转换");
	}

	/**
	 * 400 - Bad Request。处理 MethodArgumentNotValidException 异常
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public Result<String> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException e) {
		logger.error("请求的参数出现异常...->" + e.getMessage());
		return new Result<String>().failure("请求的参数出现异常");
	}

	/**
	 * 405 - Method Not Allowed。处理 HttpRequestMethodNotSupportedException 异常
	 * 是ServletException的子类,需要Servlet API支持
	 */
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Result<String> handleHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException e) {
		logger.error("请求的方法不支持...->" + e.getMessage());
		return new Result<String>().failure("请求的方法不支持");
	}

	/**
	 * 415 - Unsupported Media Type。处理 HttpMediaTypeNotSupportedException 异常
	 * 是ServletException的子类,需要Servlet API支持
	 */
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	@ExceptionHandler({ HttpMediaTypeNotSupportedException.class })
	public Result<String> handleHttpMediaTypeNotSupportedException(
			HttpMediaTypeNotSupportedException e) {
		logger.error("请求数据格式不支持...->" + e.getMessage());
		return new Result<String>().failure("请求数据格式不支持");
	}
	
	/**
	 * 500 - Internal Server Error。处理 SQLException 异常
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(SQLException.class)  
    public Result<String> handleSQLException(SQLException e) {  
		logger.error("Sql出现错误...->", e);  
		return new Result<String>().failure("Sql出现错误");
    }  

	/**
	 * 500 - Internal Server Error。处理 LockedAccountException 异常
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(LockedAccountException.class)
	public Result<String> handleLockedAccountException(LockedAccountException e) {
		logger.error("登录失败3次，账户已被锁定 ，请3分钟后再试...->" + e.getMessage());
		return new Result<String>().failure(e.getMessage());
	}
	
	/**
	 * 500 - Internal Server Error。处理 UnauthorizedException 异常
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ForbiddenException.class)
	public Result<String> handleForbiddenException(ForbiddenException e) {
		logger.error("用户名或密码不能为空...->" + e.getMessage());
		return new Result<String>().failure(e.getMessage());
	}
	
	/**
	 * 500 - Internal Server Error。处理 DisabledAccountException 异常
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DisabledAccountException.class)
	public Result<String> handleDisabledAccountException(DisabledAccountException e) {
		logger.error("该账户已被禁用 ，请联系管理员...->" + e.getMessage());
		return new Result<String>().failure(e.getMessage());
	}
	
	/**
	 * 500 - Internal Server Error。处理 UnknownAccountException 异常
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UnknownAccountException.class)
	public Result<String> handleUnknownAccountException(UnknownAccountException e) {
		logger.error("该账户不存在...->" + e.getMessage());
		return new Result<String>().failure("该账户不存在");
	}
	
	/**
	 * 处理 IncorrectCredentialsException 异常
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(IncorrectCredentialsException.class)
	public Result<String> handleIncorrectCredentialsException(IncorrectCredentialsException e) {
		logger.error("用户名或密码错误...->" + e.getMessage());
		return new Result<String>().failure("用户名或密码错误");
	}
	
	/**
	 * 500 - Internal Server Error。处理 UnauthorizedException 异常
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UnauthorizedException.class)
	public Result<String> handleUnauthorizedException(UnauthorizedException e) {
		logger.error("没有该权限...->" + e.getMessage());
		return new Result<String>().failure("没有该权限");
	}
	
	/**
	 * 500 - Internal Server Error。处理 AuthenticationException 异常
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(AuthenticationException.class)
	public Result<String> handleAuthenticationException(AuthenticationException e) {
		logger.error("权限认证异常...->" + e.getMessage());
		return new Result<String>().failure("权限认证异常");
	}
	
	/**
	 * 500 - Internal Server Error。处理 VaildCodeExpireException 异常
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(VaildCodeExpireException.class)
	public Result<String> handleVaildCodeExpireException(VaildCodeExpireException e) {
		logger.error("缓存时间过期...->" + e.getMessage());
		return new Result<String>().failure(e.getMessage());
	}
	
	/**
	 * 500 - Internal Server Error。处理 VaildCodeExpireException 异常
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NoWeatherException.class)
	public Result<String> handleNoWeatherException(NoWeatherException e) {
		logger.error("调用天气接口失败...->" + e.getMessage());
		return new Result<String>().failure(e.getMessage());
	}
	
	/**
	 * 500 - Internal Server Error。处理 Exception 异常
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public Result<String> handleException(Exception e) {
		logger.error("系统错误...->", e);
		return new Result<String>().failure("系统错误");
	}
}
