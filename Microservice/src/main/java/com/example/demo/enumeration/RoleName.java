package com.example.demo.enumeration;

import org.springframework.security.core.GrantedAuthority;

public enum RoleName implements GrantedAuthority{
	ROLE_USER, ROLE_ADMIN;

	@Override
	public String getAuthority() {
		return name();
	}

}
