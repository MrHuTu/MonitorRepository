package com.zhongda.monitor.account.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Title:自定义注解     
 * Description: 标识是否忽略REST安全性检查
 * @Author dengzm
 * @Date 2018年1月11日 下午3:49:35
 */
@Target(ElementType.METHOD) 
@Retention(RetentionPolicy.RUNTIME) 
@Documented
public @interface IgnoreSecurity {

}