package com.workstore.user.modules.account.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workstore.common.modules.account.domain.Account;
import com.workstore.common.modules.account.domain.AccountRepository;
import com.workstore.user.infra.security.CurrentUser;
import com.workstore.user.infra.security.UserPrincipal;
import com.workstore.user.modules.account.exception.AccountNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
	private final AccountRepository accountRepository;

	@GetMapping
	@PreAuthorize("hasRole('USER')")
	public Account getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
		return accountRepository.findById(userPrincipal.getId())
			.orElseThrow(AccountNotFoundException::new);
	}
}
