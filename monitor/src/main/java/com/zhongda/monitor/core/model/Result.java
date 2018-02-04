package com.zhongda.monitor.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Title : 请求统一返回结果
 * Description : 对所有请求返回的数据封装到该类型对象中返回
 * @Author dengzm
 * @Date 2018年1月16日 下午3:18:23
 */
@ApiModel
public class Result<T> {
	
	public static final Integer SUCCESS = 0;//请求失败
	public static final Integer FAILURE = 1;//请求成功
	
	//状态码 
	@ApiModelProperty(value = "状态码", name = "状态码")
	private Integer code;
	//错误提示信息
	@ApiModelProperty(value = "状态码描述", name = "状态码描述")
	private String msg;
	//返回数据
	@ApiModelProperty(value = "数据对象", name = "数据对象")
	private T data;
	
	public Integer getCode() {
		return code;
	}	
	
	public Result<T> setCode(Integer code) {
		this.code = code;
		return this;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public Result<T> setMsg(String msg) {
		this.msg = msg;
		return this;
	}
	
	public T getData() {
		return data;
	}
	
	public Result<T> setData(T data) {
		this.data = data;
		return this;
	}
	
}
