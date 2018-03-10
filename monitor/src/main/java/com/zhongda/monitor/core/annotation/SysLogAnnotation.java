package com.zhongda.monitor.core.annotation;

import java.lang.annotation.*;

/**
 * 类SysLogAnnotation的功能描述:
 * 系统日志注解
 * @auther dengzm
 * @date 2018-01-10 15:16:47
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogAnnotation {
	
	/**
	 * 可添加占位符，解析时会以对应顺序的参数替换
	 * 占位符格式${1},大括号的数字代表该方法传入参数的索引值,索引值从0开始
	 */
	String value() default "";

}
