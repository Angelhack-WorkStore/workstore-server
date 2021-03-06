package com.workstore.user.modules.account.authMock;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import com.workstore.common.modules.account.domain.Account;
import com.workstore.user.infra.security.CustomUserDetailsService;
import com.workstore.user.modules.account.api.request.SignUpRequest;
import com.workstore.user.modules.account.service.UserAccountService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WithAccountSecurityContextFactory implements WithSecurityContextFactory<WithAccount> {
	private final UserAccountService accountService;
	private final CustomUserDetailsService customUserDetailsService;

	@Override
	public SecurityContext createSecurityContext(WithAccount withAccount) {
		String nickname = withAccount.value();

		SignUpRequest request = new SignUpRequest();
		request.setName(nickname);
		request.setEmail(nickname + "@gmail.com");
		request.setPassword("password!");
		Account account = accountService.saveNewAccount(request);

		UserDetails principal = customUserDetailsService.loadUserById(account.getId());
		Authentication authentication = new UsernamePasswordAuthenticationToken(
			principal, principal.getPassword(), principal.getAuthorities());
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authentication);
		return context;
	}
}
