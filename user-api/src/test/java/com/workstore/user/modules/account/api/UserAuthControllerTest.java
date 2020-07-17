package com.workstore.user.modules.account.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import com.workstore.common.modules.account.domain.Account;
import com.workstore.common.modules.account.domain.AccountRepository;
import com.workstore.user.modules.account.api.request.LoginRequest;
import com.workstore.user.modules.account.api.request.SignUpRequest;
import com.workstore.user.modules.account.service.UserAccountService;
import com.workstore.user.modules.common.IntegrationTests;

@Disabled
class UserAuthControllerTest extends IntegrationTests {
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private UserAccountService accountService;

	@AfterEach
	void tearDown() {
		accountRepository.deleteAll();
	}

	@Test
	void register() throws Exception {
		SignUpRequest signUpRequest = new SignUpRequest();
		signUpRequest.setEmail("msolo021015@naver.com");
		signUpRequest.setName("rebwon");
		signUpRequest.setPassword("1234");

		mockMvc.perform(post("/api/auth/signup")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(signUpRequest))
		)
			.andDo(print())
			.andExpect(status().is3xxRedirection());
	}

	@Test
	@Transactional
	void login() throws Exception {
		SignUpRequest signUpRequest = new SignUpRequest();
		signUpRequest.setEmail("msolo021015@naver.com");
		signUpRequest.setName("rebwon");
		signUpRequest.setPassword("1234");

		Account account = accountService.register(signUpRequest);
		accountService.completeSignUp(account);

		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setEmail("msolo021015@naver.com");
		loginRequest.setPassword("1234");

		mockMvc.perform(post("/api/auth/login")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(loginRequest))
		)
			.andDo(print())
			.andExpect(status().isOk());
	}
}