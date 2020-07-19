package com.workstore.admin.modules.account.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

import com.workstore.admin.modules.account.authMock.WithAccount;
import com.workstore.admin.modules.common.IntegrationTests;

@Disabled
class AccountControllerTest extends IntegrationTests {
	private static final String ACCOUNT_API = "/api/admin/accounts";

	@Test
	@WithAccount("rebwon")
	@DisplayName("사업자 정보 조회 - 성공")
	void given_Header_Token_When_FindAccount_Then_Success_HTTP_CODE_200() throws Exception {
		mockMvc.perform(get(ACCOUNT_API)
			.header(HttpHeaders.AUTHORIZATION, getAuthenticationToken())
		)
			.andDo(print())
			.andExpect(status().isOk());
	}
}