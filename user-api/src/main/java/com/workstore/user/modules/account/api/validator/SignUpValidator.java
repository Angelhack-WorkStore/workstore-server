package com.workstore.user.modules.account.api.validator;

import org.springframework.stereotype.Component;

import com.workstore.common.modules.account.domain.AccountRepository;
import com.workstore.user.modules.account.api.request.SignUpRequest;
import com.workstore.user.modules.account.exception.EmailAlreadyUsedException;
import com.workstore.user.modules.account.exception.NicknameAlreadyUsedException;
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
