package com.workstore.user.modules.account.api;

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

import com.workstore.user.modules.account.api.request.LoginRequest;
import com.workstore.user.modules.account.api.request.SignUpRequest;
import com.workstore.user.modules.account.api.response.TokenResponse;
import com.workstore.user.modules.account.api.validator.LoginValidator;
import com.workstore.user.modules.account.api.validator.SignUpValidator;
import com.workstore.user.modules.account.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserAuthController {
	private final UserAccountService accountService;
	private final SignUpValidator signUpValidator;
	private final LoginValidator loginValidator;

	@GetMapping("/check-email-token")
	public ResponseEntity<?> checkEmailToken(String token, String email) throws URISyntaxException {
		accountService.checkEmailToken(token, email);
		URI redirectUri = new URI("http://localhost:3001/login");
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

		URI redirectUri = new URI("http://localhost:3001/login");
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(redirectUri);
		return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
	}
}
