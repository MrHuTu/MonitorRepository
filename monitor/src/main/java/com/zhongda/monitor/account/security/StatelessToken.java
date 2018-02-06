package com.zhongda.monitor.account.security;

import org.apache.shiro.authc.AuthenticationToken;

public class StatelessToken implements AuthenticationToken {
	
	private static final long serialVersionUID = 1L;
	
	public static final String HEADER = "Authorization";
	
	/** 用户名  */
	private String principal;
	/** 密码  */
	private String credentials;
	/** token  */
	private String token;
	
	public StatelessToken() {
		
	}
	
	public StatelessToken(String token) {
		this.token = token;
	}
	
	public StatelessToken(String principal, String credentials) {
		this.principal = principal;
		this.credentials = credentials;
	}
	
	public StatelessToken(String principal, String credentials, String token) {
		this.principal = principal;
		this.credentials = credentials;
		this.token = token;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}

	@Override
	public Object getCredentials() {
		return credentials;
	}

	public String getToken() {
		return token;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}

