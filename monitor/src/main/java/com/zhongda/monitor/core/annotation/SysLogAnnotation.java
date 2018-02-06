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

	String value() default "";
}
