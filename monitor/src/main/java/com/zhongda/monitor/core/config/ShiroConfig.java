package com.zhongda.monitor.core.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zhongda.monitor.account.StatelessDefaultSubjectFactory;
import com.zhongda.monitor.account.filter.StatelessTokenFilter;
import com.zhongda.monitor.account.security.StatelessRealm;

@Configuration
public class ShiroConfig {
	
	/**
	 * shiro的filter对应的bean Shiro的web过滤器
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter() {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(getDefaultWebSecurityManager());
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setSuccessUrl("/index");
		shiroFilterFactoryBean.setUnauthorizedUrl("/error");
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		filterChainDefinitionMap.put("/v2/api-docs", "anon");
		filterChainDefinitionMap.put("/swagger-ui.html", "anon");
		filterChainDefinitionMap.put("/swagger-resources", "anon");
		filterChainDefinitionMap.put("/swagger-resources/**", "anon");
		filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");
		filterChainDefinitionMap.put("/token/login", "anon");
		filterChainDefinitionMap.put("/webSocket/**", "anon");
		filterChainDefinitionMap.put("/valiCode/**", "anon");
		filterChainDefinitionMap.put("/user/forgetPassword", "anon");
		filterChainDefinitionMap.put("/user/validateUserExist", "anon");
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        filters.put("authc", new StatelessTokenFilter());
		shiroFilterFactoryBean.setFilters(filters);
		return shiroFilterFactoryBean;
	}
	
	/**
	 * shiro安全认证授权realm
	 **/
	@ConditionalOnMissingBean(StatelessRealm.class)
	public StatelessRealm getAuthorizingRealm() {
		return new StatelessRealm();
	}
	
	/**
     * Subject工厂管理器(使用自定义无状态工厂)
     * @return
     */
    @Bean
    public DefaultWebSubjectFactory getSubjectFactory(){
    	return new StatelessDefaultSubjectFactory();
    }
	
	/**
	 * shiro缓存管理器,使用Ehcache实现
	 */
	/*@Bean(name = "shiroEhcacheManager")
	public EhCacheManager getEhCacheManager() {
		EhCacheManager ehCacheManager = new EhCacheManager();
		ehCacheManager.setCacheManagerConfigFile("classpath:ehcache/ehcache-shiro.xml");
		return ehCacheManager;
	}*/
	
	/**
	 * shiro会话管理器
	 */
	@Bean(name = "sessionManager")
	public DefaultWebSessionManager getDefaultWebSessionManager(){
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		// 关闭session定时检查，通过setSessionValidationSchedulerEnabled禁用掉会话调度器
        sessionManager.setSessionValidationSchedulerEnabled(false);
		return sessionManager;
	}
	
	/**
	 * shiro安全管理器
	 */
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(getAuthorizingRealm());
		// 替换默认的DefaultSubjectFactory，用于关闭session功能
        securityManager.setSubjectFactory(getSubjectFactory());
        securityManager.setSessionManager(getDefaultWebSessionManager());
		//securityManager.setCacheManager(getEhCacheManager());
		// 关闭session存储，禁用Session作为存储策略的实现，但它没有完全地禁用Session所以需要配合SubjectFactory中的context.setSessionCreationEnabled(false)
        ((DefaultSessionStorageEvaluator) ((DefaultSubjectDAO)securityManager.getSubjectDAO()).getSessionStorageEvaluator()).setSessionStorageEnabled(false);
        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
	}
	
	/**
	 * Shiro生命周期处理器
	 */
	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
}