package com.workstore.user.infra.security;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.workstore.common.modules.account.domain.Account;

public class UserPrincipal extends User implements OAuth2User {
	private final Account account;
	private Map<String, Object> attributes;

	public UserPrincipal(Account account) {
		super(account.getEmail(), account.getPassword(), authorities(account));
		this.account = account;
	}

	private static Collection<? extends GrantedAuthority> authorities(Account account) {
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + account.getRole()));
	}

	public static UserPrincipal create(Account account) {
		return new UserPrincipal(account);
	}

	public static UserPrincipal create(Account account, Map<String, Object> attributes) {
		UserPrincipal userPrincipal = UserPrincipal.create(account);
		userPrincipal.setAttributes(attributes);
		return userPrincipal;
	}

	public Account getAccount() {
		return this.account;
	}

	public Long getId() {
		return this.account.getId();
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	@Override
	public String getName() {
		return String.valueOf(this.account.getId());
	}
}