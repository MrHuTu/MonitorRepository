package com.zhongda.monitor.account.exception;

import org.apache.shiro.authc.AccountException;

/**
 * Title : ForbiddenException管理
 * Description : 处理用户名或密码为空的异常信息
 * @Author dengzm
 * @Date 2018年2月23日 下午12:45:17
 */
public class ForbiddenException extends AccountException{

	private static final long serialVersionUID = 1L;
	
	private String msg;

	public ForbiddenException(String msg) {
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
