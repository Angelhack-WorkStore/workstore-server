package com.workstore.user.infra.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workstore.common.modules.account.domain.Account;
import com.workstore.common.modules.account.domain.AccountRepository;
import com.workstore.user.modules.account.exception.AccountNotFoundException;
import com.workstore.user.modules.account.exception.EmailNotVerificationException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private final AccountRepository accountRepository;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Account account = accountRepository.findByEmail(email)
			.orElseThrow(() ->
				new UsernameNotFoundException("User not found with email : " + email)
			);

		if(!account.isEmailVerified()) {
			throw new EmailNotVerificationException();
		}

		return UserPrincipal.create(account);
	}

	@Transactional(readOnly = true)
	public UserDetails loadUserById(Long id) {
		Account account = accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
		return UserPrincipal.create(account);
	}
}
