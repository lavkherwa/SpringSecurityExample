package com.example.security.pojo;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.security.model.User;

public class MyUserDetailsForJpa implements UserDetails {

	private static final long serialVersionUID = -6726905448716038875L;

	private String userName;
	private String password;
	private boolean active;
	private List<GrantedAuthority> authorities;

	public MyUserDetailsForJpa(User user) {
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.active = user.isActive();

		this.authorities = Arrays//
				.stream(user.getRoles().split(","))//
				.map(SimpleGrantedAuthority::new)//
				.collect(Collectors.toList());

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<GrantedAuthority> getRoles() {
		return authorities;
	}

	public void setRoles(List<GrantedAuthority> roles) {
		this.authorities = roles;
	}

	public void setPassword(String password) {
		this.password = password;
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
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return active;
	}

	@Override
	public boolean isAccountNonLocked() {

		return active;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return active;
	}

	@Override
	public boolean isEnabled() {
		return active;
	}

}
