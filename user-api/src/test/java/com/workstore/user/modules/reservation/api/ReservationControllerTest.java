package com.workstore.user.modules.reservation.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.workstore.user.modules.account.authMock.WithAccount;
import com.workstore.user.modules.common.IntegrationTests;
import com.workstore.user.modules.common.mock.Fixtures;
import com.workstore.user.modules.reservation.api.request.ReservationPayload;

class ReservationControllerTest extends IntegrationTests {
	private static final String RESERVE_API = "/api/reservations";

	@Test
	@WithAccount("rebwon")
	@DisplayName("예약 등록 - 성공")
	void given_ReservationPayload_When_Reserve_Then_Success_HTTP_CODE_201() throws Exception {
		ReservationPayload payload = Fixtures.registerReservationPayload();
		mockMvc.perform(post(RESERVE_API)
			.content(objectMapper.writeValueAsString(payload))
			.contentType(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, getAuthenticationToken())
		)
			.andDo(print())
			.andExpect(status().isOk())
		;
	}
}