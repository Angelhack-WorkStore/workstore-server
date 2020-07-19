package com.workstore.user.modules.account.api.validator;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.workstore.common.modules.account.domain.Account;
import com.workstore.common.modules.account.domain.AccountRepository;
import com.workstore.user.modules.account.api.request.LoginRequest;
import com.workstore.user.modules.account.exception.AccountNotFoundException;
import com.workstore.user.modules.account.exception.PasswordMismatchedException;
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
