package com.workstore.admin.modules.account.api.validator;

import org.springframework.stereotype.Component;

import com.workstore.admin.modules.account.api.request.SignUpRequest;
import com.workstore.admin.modules.account.exception.EmailAlreadyUsedException;
import com.workstore.admin.modules.account.exception.NicknameAlreadyUsedException;
import com.workstore.common.modules.account.domain.AccountRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SignUpValidator {
	private final AccountRepository accountRepository;

	public void validate(SignUpRequest signUpRequest) {
		if(accountRepository.existsByEmail(signUpRequest.getEmail()))
			throw new EmailAlreadyUsedException();
		if(accountRepository.existsByNickname(signUpRequest.getName()))
			throw new NicknameAlreadyUsedException();
	}
}
