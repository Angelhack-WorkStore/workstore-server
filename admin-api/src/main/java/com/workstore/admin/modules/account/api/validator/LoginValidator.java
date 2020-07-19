package com.workstore.admin.modules.account.api.validator;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.workstore.admin.modules.account.api.request.LoginRequest;
import com.workstore.admin.modules.account.exception.AccountNotFoundException;
import com.workstore.admin.modules.account.exception.PasswordMismatchedException;
import com.workstore.common.modules.account.domain.Account;
import com.workstore.common.modules.account.domain.AccountRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginValidator {
	private final AccountRepository accountRepository;
	private final PasswordEncoder passwordEncoder;

	public void validate(LoginRequest request) {
		Account account = accountRepository.findByEmail(request.getEmail()).orElseThrow(AccountNotFoundException::new);
		if(!passwordEncoder.matches(request.getPassword(), account.getPassword()))
			throw new PasswordMismatchedException("Your input password is not correct");
	}
}
