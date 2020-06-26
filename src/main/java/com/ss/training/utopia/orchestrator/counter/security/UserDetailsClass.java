package com.ss.training.utopia.orchestrator.counter.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ss.training.utopia.orchestrator.security.User;

/**
 * @author Justin O'Brien
 */
public class UserDetailsClass implements UserDetails {

	private static final long serialVersionUID = 1557339291337891604L;
	private String username;
	private String password;
	private List<GrantedAuthority> authorities;

	public UserDetailsClass(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
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