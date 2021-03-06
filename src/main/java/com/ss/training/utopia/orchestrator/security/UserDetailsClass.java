package com.ss.training.utopia.orchestrator.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.ss.training.utopia.orchestrator.entity.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Trevor Huis in 't Veld
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
		this.authorities = Arrays.stream(user.getRole().split(",")).map(role -> "ROLE_" + role)
				.map(SimpleGrantedAuthority::new).collect(Collectors.toList());
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