package com.workstore.admin.modules.account.service.component;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.workstore.admin.infra.security.TokenProvider;
import com.workstore.admin.modules.account.api.request.LoginRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginManager {
	private final TokenProvider tokenProvider;
	private final AuthenticationManager authenticationManager;

	public String login(LoginRequest request) {
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				request.getEmail(),
				request.getPassword()
			)
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return tokenProvider.createToken(authentication);
	}
}
