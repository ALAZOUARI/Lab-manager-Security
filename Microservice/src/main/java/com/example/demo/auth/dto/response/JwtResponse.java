package com.example.demo.auth.dto.response;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private String cin;
	private Collection<? extends GrantedAuthority> authorities;

	public JwtResponse(String token, String cin, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.token = token;
		this.cin = cin;
		this.authorities = authorities;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String token) {
		this.token = token;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String type) {
		this.type = type;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

}
