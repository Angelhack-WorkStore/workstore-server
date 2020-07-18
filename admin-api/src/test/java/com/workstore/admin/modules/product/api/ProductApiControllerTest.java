package com.workstore.admin.modules.product.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.workstore.admin.modules.account.authMock.WithAccount;
import com.workstore.admin.modules.common.IntegrationTests;
import com.workstore.admin.modules.common.mock.Fixtures;
import com.workstore.admin.modules.product.api.request.ProductPayload;
import com.workstore.common.modules.account.domain.AccountRepository;
import com.workstore.common.modules.product.domain.Product;
import com.workstore.common.modules.product.domain.ProductRepository;

//@Disabled
class ProductApiControllerTest extends IntegrationTests {
	private static final String PRODUCT_URI = "/api/admin/products";

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private AccountRepository accountRepository;
	private Product setUp;

	@BeforeEach
	void setUp() {
		setUp = Fixtures.product().build();
		productRepository.save(setUp);
	}

	@AfterEach
	void tearDown() {
		productRepository.deleteAll();
		accountRepository.deleteAll();
	}

	@Test
	@WithAccount("rebwon")
	@DisplayName("상품 등록 - 성공")
	void given_ProductPayload_When_Register_Then_Success_HTTP_CODE_201() throws Exception {
		ProductPayload payload = Fixtures.registerProductPayload().build();
		mockMvc.perform(post(PRODUCT_URI)
			.content(objectMapper.writeValueAsString(payload))
			.contentType(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, getAuthenticationToken())
		)
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	@WithAccount("rebwon")
	@DisplayName("상품 수정 - 성공")
	void given_ProductPayload_When_Update_Then_Success_HTTP_CODE_200() throws Exception {
		ProductPayload payload = Fixtures.registerProductPayload().build();
		mockMvc.perform(put(PRODUCT_URI + "/" + setUp.getId())
			.content(objectMapper.writeValueAsString(payload))
			.contentType(MediaType.APPLICATION_JSON)
			.header(HttpHeaders.AUTHORIZATION, getAuthenticationToken())
		)
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	@WithAccount("rebwon")
	@DisplayName("상품 조회 - 성공")
	void given_ProductId_When_FineOne_Then_Success_HTTP_CODE_200() throws Exception {
		mockMvc.perform(get(PRODUCT_URI + "/" + setUp.getId())
			.header(HttpHeaders.AUTHORIZATION, getAuthenticationToken())
		)
			.andDo(print())
			.andExpect(status().isOk());
	}
}