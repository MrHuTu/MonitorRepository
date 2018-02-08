package com.zhongda.monitor.account.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * Title: 自定义的RuntimeException
 * Description:没有传入token做验证时抛出
 * @Author dengzm
 * @Date 2018年1月11日 下午4:03:47
 */
public class NoStatelessTokenException extends AuthenticationException {

	private static final long serialVersionUID = 1L;
	
	private String msg;

	public NoStatelessTokenException(String msg) {
		super();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
