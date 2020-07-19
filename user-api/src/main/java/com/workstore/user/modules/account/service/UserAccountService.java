package com.workstore.user.modules.account.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workstore.common.modules.account.domain.Account;
import com.workstore.common.modules.account.domain.AccountRepository;
import com.workstore.common.modules.account.domain.AuthProvider;
import com.workstore.common.modules.account.domain.Role;
import com.workstore.user.modules.account.api.request.LoginRequest;
import com.workstore.user.modules.account.api.request.SignUpRequest;
import com.workstore.user.modules.account.exception.AccountNotFoundException;
import com.workstore.user.modules.account.exception.EmailTokenInvalidException;
import com.workstore.user.modules.account.service.component.LoginManager;
import com.workstore.user.modules.account.service.component.MailManager;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserAccountService {
	private final PasswordEncoder passwordEncoder;
	private final AccountRepository accountRepository;
	private final MailManager mailManager;
	private final LoginManager loginManager;

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
		account.setRole(Role.USER);
		return this.accountRepository.save(account);
	}

	public void checkEmailToken(String token, String email) {
		Account account = accountRepository.findByEmail(email).orElseThrow(AccountNotFoundException::new);
		if(!account.isValidToken(token)) {
			throw new EmailTokenInvalidException();
		}
		account.completeSignUp();
	}

	// Local Test
	public void completeSignUp(Account account) {
		account.completeSignUp();
	}

	public String login(LoginRequest request) {
		return loginManager.login(request);
	}
}
