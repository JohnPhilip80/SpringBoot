package com.cca.john.book.models;

import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AppUserPrincipal implements UserDetails {
	
	private AppUser appUser;
	
	public AppUserPrincipal(AppUser appUser) {
		this.appUser = appUser;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}
	@Override
	public String getPassword() {
		return appUser.getPassword();
	}
	@Override
	public String getUsername() {
		return appUser.getEmail();
	}
}
