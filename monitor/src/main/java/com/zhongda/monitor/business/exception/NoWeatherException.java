package com.zhongda.monitor.business.exception;


/**
 * Title : NoWeatherException管理
 * Description : 处理获取天气信息失败的异常信息
 * @Author dengzm
 * @Date 2018年2月23日 下午12:45:17
 */
public class NoWeatherException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String msg;
	
	public NoWeatherException() {
		super();
	}
	
	public NoWeatherException(String msg) {
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
