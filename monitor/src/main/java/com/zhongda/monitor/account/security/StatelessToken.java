package com.zhongda.monitor.account.security;

import org.apache.shiro.authc.AuthenticationToken;

public class StatelessToken implements AuthenticationToken {
	
	private static final long serialVersionUID = 1L;
	
	//用于验证使用：Header中的名字
    public static final String DEFAULT_TOKEN_NAME = "Authorization";

    //约定以什么信息开头
    public static final String TOKEN_HEADER_PREFIX = "Bearer";
	
	/** 用户名  */
	private String principal;
	/** token  */
	private String token;
	
	public StatelessToken() {
		
	}
	
	public StatelessToken(String token) {
		this.token = token;
	}
	
	public StatelessToken(String principal, String token) {
		this.principal = principal;
		this.token = token;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}

	@Override
	public Object getCredentials() {
		return token;
	}

	public String getToken() {
		return token;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public void setCredentials(String credentials) {
		this.token = credentials;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}

