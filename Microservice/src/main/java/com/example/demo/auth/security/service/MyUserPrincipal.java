package com.example.demo.auth.security.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entities.Membre;

public class MyUserPrincipal implements UserDetails{
	
	    private Membre user;

	    public MyUserPrincipal(Membre user) {
	        this.user = user;
	    }

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			List<GrantedAuthority> authorities = new java.util.ArrayList<>(Collections.emptyList());
			authorities.add((GrantedAuthority) () -> user.getRole().toString());
			return authorities;
		}

		@Override
		public String getPassword() {
			
			return user.getPassword();
		}

		@Override
		public String getUsername() {
			return user.getCin();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}
}
