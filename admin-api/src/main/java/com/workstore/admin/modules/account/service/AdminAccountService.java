package com.workstore.admin.modules.account.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.workstore.admin.infra.config.AdminAppProperties;
import com.workstore.admin.infra.mail.MailMessage;
import com.workstore.admin.infra.mail.MailService;
import com.workstore.admin.infra.security.TokenProvider;
import com.workstore.admin.modules.account.api.request.LoginRequest;
import com.workstore.admin.modules.account.api.request.SignUpRequest;
import com.workstore.common.modules.account.domain.Account;
import com.workstore.common.modules.account.domain.AccountRepository;
import com.workstore.common.modules.account.domain.AuthProvider;
import com.workstore.common.modules.account.domain.Role;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminAccountService {
	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder passwordEncoder;
	private final AccountRepository accountRepository;
	private final MailService mailService;
	private final AdminAppProperties appProperties;
	private final TemplateEngine templateEngine;
	private final TokenProvider tokenProvider;

	public Account register(SignUpRequest request) {
		Account newAccount = saveNewAccount(request);
		sendSignUpConfirmEmail(newAccount);
		return newAccount;
	}

	private void sendSignUpConfirmEmail(Account newAccount) {
		Context context = new Context();
		context.setVariable("link", "/api/auth/check-email-token?token=" + newAccount.getEmailCheckToken() +
			"&email=" + newAccount.getEmail());
		context.setVariable("nickname", newAccount.getNickname());
		context.setVariable("linkName", "이메일 인증하기");
		context.setVariable("message", "WorkStore 서비스를 사용하려면 링크를 클릭하세요.");
		context.setVariable("host", appProperties.getMail().getHost());
		String message = templateEngine.process("mail/simple-link", context);

		MailMessage emailMessage = MailMessage.builder()
			.to(newAccount.getEmail())
			.subject("WorkStore, 회원 가입 인증")
			.message(message)
			.build();

		mailService.send(emailMessage);
	}

	public Account saveNewAccount(SignUpRequest request) {
		Account account = new Account();
		account.setNickname(request.getName());
		account.setEmail(request.getEmail());
		account.setPassword(request.getPassword());
		account.setProvider(AuthProvider.local);
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		account.generateEmailCheckToken();
		account.setRole(Role.ADMIN);
		return this.accountRepository.save(account);
	}

	public void completeSignUp(Account account) {
		account.completeSignUp();
	}

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
