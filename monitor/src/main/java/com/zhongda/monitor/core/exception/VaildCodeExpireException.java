package com.zhongda.monitor.core.exception;


/**
 * Title : VaildCodeExpireException管理
 * Description : 处理验证码过期的异常信息
 * @Author dengzm
 * @Date 2018年2月23日 下午12:45:17
 */
public class VaildCodeExpireException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String msg;
	
	public VaildCodeExpireException() {
		super();
	}
	
	public VaildCodeExpireException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
