package com.zhongda.monitor.core.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.zhongda.monitor.account.StatelessDefaultSubjectFactory;
import com.zhongda.monitor.account.filter.StatelessTokenFilter;
import com.zhongda.monitor.account.security.StatelessRealm;

@Configuration
//@ConfigurationProperties(prefix = "shiroFilter")
public class ShiroConfig {

	/**
	 * DelegatingFilterProxy通过代理模式将Spring容器中的bean和filter关联起来
	 */
	@Bean
	public FilterRegistrationBean delegatingFilterProxy() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
		// 设置targetFilterLifecycle为true, 由servlet控制filter生命周期
		delegatingFilterProxy.setTargetFilterLifecycle(true); 
		delegatingFilterProxy.setTargetBeanName("shiroFilter");
		filterRegistrationBean.setFilter(delegatingFilterProxy);
		filterRegistrationBean.setEnabled(true);  
		filterRegistrationBean.addUrlPatterns("/*");   
		filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST);
		return filterRegistrationBean;
	}
	
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
		filterChainDefinitionMap.put("/token", "anon");
		//filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        filters.put("authcx", new StatelessTokenFilter());
		shiroFilterFactoryBean.setFilters(filters);
		return shiroFilterFactoryBean;
	}
	
	/**
	 * shiro密码加密器
	 */
	@Bean(name = "md5CredentialsMatcher")
	public HashedCredentialsMatcher getHashedCredentialsMatcher() {
		HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher(
				Md5Hash.ALGORITHM_NAME);
		md5CredentialsMatcher.setHashIterations(1024);
		return md5CredentialsMatcher;
	}
	
	/**
	 * shiro安全认证授权realm
	 */
	@Bean(name = "statelessRealm")
	public AuthorizingRealm getAuthorizingRealm() {
		StatelessRealm statelessRealm = new StatelessRealm();
		statelessRealm.setCredentialsMatcher(getHashedCredentialsMatcher());
		return statelessRealm;
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
	@Bean(name = "shiroEhcacheManager")
	public EhCacheManager getEhCacheManager() {
		EhCacheManager ehCacheManager = new EhCacheManager();
		ehCacheManager.setCacheManagerConfigFile("classpath:ehcache/ehcache-shiro.xml");
		return ehCacheManager;
	}
	
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
		securityManager.setCacheManager(getEhCacheManager());
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