package com.workstore.admin.modules.account.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workstore.admin.modules.account.api.request.LoginRequest;
import com.workstore.admin.modules.account.api.request.SignUpRequest;
import com.workstore.admin.modules.account.service.component.LoginManager;
import com.workstore.admin.modules.account.service.component.MailManager;
import com.workstore.common.modules.account.domain.Account;
import com.workstore.common.modules.account.domain.AccountRepository;
import com.workstore.common.modules.account.domain.AuthProvider;
import com.workstore.common.modules.account.domain.Role;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminAccountService {
	private final PasswordEncoder passwordEncoder;
	private final AccountRepository accountRepository;
	private final LoginManager loginManager;
	private final MailManager mailManager;

	public Account register(SignUpRequest request) {
		Account newAccount = saveNewAccount(request);
		mailManager.sendSignUpConfirmEmail(newAccount);
		return newAccount;
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
		return loginManager.login(request);
	}
}
