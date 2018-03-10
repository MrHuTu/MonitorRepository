package com.zhongda.monitor.core.exception;


/**
 * Title : PlaceholderNotMatchParamException管理
 * Description : 处理占位符和参数不匹配的异常信息
 * @Author dengzm
 * @Date 2018年2月23日 下午12:45:17
 */
public class PlaceholderNotMatchParamException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String msg;
	
	public PlaceholderNotMatchParamException() {
		super();
	}
	
	public PlaceholderNotMatchParamException(String msg) {
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
