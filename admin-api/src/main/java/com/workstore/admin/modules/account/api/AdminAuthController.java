package com.workstore.admin.modules.account.api;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workstore.admin.infra.security.TokenProvider;
import com.workstore.admin.modules.account.api.request.LoginRequest;
import com.workstore.admin.modules.account.api.request.SignUpRequest;
import com.workstore.admin.modules.account.api.response.TokenResponse;
import com.workstore.admin.modules.account.exception.AccountNotFoundException;
import com.workstore.admin.modules.account.exception.EmailAlreadyUsedException;
import com.workstore.admin.modules.account.exception.EmailTokenInvalidException;
import com.workstore.admin.modules.account.exception.NicknameAlreadyUsedException;
import com.workstore.admin.modules.account.service.AdminAccountService;
import com.workstore.common.modules.account.domain.Account;
import com.workstore.common.modules.account.domain.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AdminAuthController {
	private final AuthenticationManager authenticationManager;
	private final AccountRepository accountRepository;
	private final AdminAccountService accountService;
	private final TokenProvider tokenProvider;

	@GetMapping("/check-email-token")
	public ResponseEntity<?> checkEmailToken(String token, String email) throws URISyntaxException {
		Account account = accountRepository.findByEmail(email).orElseThrow(AccountNotFoundException::new);

		if(!account.isValidToken(token)) {
			throw new EmailTokenInvalidException();
		}

		accountService.completeSignUp(account);
		URI redirectUri = new URI("http://localhost:3000/login");
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(redirectUri);
		return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				loginRequest.getEmail(),
				loginRequest.getPassword()
			)
		);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = tokenProvider.createToken(authentication);
		return ResponseEntity.ok(new TokenResponse(token));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) throws URISyntaxException {
		if(accountRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new EmailAlreadyUsedException();
		}
		if(accountRepository.existsByNickname(signUpRequest.getName())) {
			throw new NicknameAlreadyUsedException();
		}

		accountService.register(signUpRequest);

		URI redirectUri = new URI("http://localhost:3000/login");
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(redirectUri);

		return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
	}
}
