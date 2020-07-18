package com.workstore.user.modules.account.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workstore.common.modules.account.domain.Account;
import com.workstore.common.modules.account.domain.AccountRepository;
import com.workstore.user.infra.security.CurrentUser;
import com.workstore.user.modules.account.exception.AccountNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
	private final AccountRepository accountRepository;

	@GetMapping
	public Account getCurrentUser(@CurrentUser Account account) {
		return accountRepository.findById(account.getId())
			.orElseThrow(AccountNotFoundException::new);
	}
}
