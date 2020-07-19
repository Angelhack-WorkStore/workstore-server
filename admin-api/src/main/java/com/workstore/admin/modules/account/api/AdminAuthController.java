package com.workstore.admin.modules.account.api;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workstore.admin.modules.account.api.request.LoginRequest;
import com.workstore.admin.modules.account.api.request.SignUpRequest;
import com.workstore.admin.modules.account.api.response.TokenResponse;
import com.workstore.admin.modules.account.api.validator.LoginValidator;
import com.workstore.admin.modules.account.api.validator.SignUpValidator;
import com.workstore.admin.modules.account.exception.AccountNotFoundException;
import com.workstore.admin.modules.account.exception.EmailTokenInvalidException;
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
	private final AccountRepository accountRepository;
	private final AdminAccountService accountService;
	private final LoginValidator loginValidator;
	private final SignUpValidator signUpValidator;

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
		loginValidator.validate(loginRequest);
		String token = accountService.login(loginRequest);
		return ResponseEntity.ok(new TokenResponse(token));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) throws URISyntaxException {
		signUpValidator.validate(signUpRequest);
		accountService.register(signUpRequest);

		URI redirectUri = new URI("http://localhost:3000/login");
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(redirectUri);
		return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
	}
}
