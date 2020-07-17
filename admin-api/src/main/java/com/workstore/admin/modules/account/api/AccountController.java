package com.workstore.admin.modules.account.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workstore.admin.infra.security.CurrentUser;
import com.workstore.admin.infra.security.UserPrincipal;
import com.workstore.admin.modules.account.exception.AccountNotFoundException;
import com.workstore.common.modules.account.domain.Account;
import com.workstore.common.modules.account.domain.AccountRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/accounts")
@RequiredArgsConstructor
public class AccountController {
	private final AccountRepository accountRepository;

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public Account getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
		return accountRepository.findById(userPrincipal.getId())
			.orElseThrow(AccountNotFoundException::new);
	}
}
