package com.workstore.user.modules.main;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.workstore.user.modules.common.IntegrationTests;

class MainControllerTest extends IntegrationTests {

	@Test
	@DisplayName("메인 페이지 조회 - 성공")
	void when_MainPage_Then_FindProducts() throws Exception {
		mockMvc.perform(get("/"))
			.andDo(print())
			.andExpect(status().isOk());
	}
}