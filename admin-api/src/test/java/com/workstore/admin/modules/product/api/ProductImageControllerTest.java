package com.workstore.admin.modules.product.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import com.workstore.admin.modules.common.IntegrationTests;

@Disabled
class ProductImageControllerTest extends IntegrationTests {
	private static final String IMAGE_API = "/api/admin/products/images";

	@Test
	@DisplayName("상품 이미지 저장 - 성공")
	void givenImage_When_Upload_Then_Success_HTTP_CODE_200() throws Exception {
		MockMultipartFile image1 = new MockMultipartFile("file", "fileName1.jpg", "image/jpg", "image".getBytes());
		MockMultipartFile image2 = new MockMultipartFile("file", "fileName2.jpg", "image/jpg", "image".getBytes());

		mockMvc.perform(multipart(IMAGE_API)
			.file(image1)
			.file(image2)
			.param("imageType", "THUMBNAIL")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.MULTIPART_FORM_DATA)
		)
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	@DisplayName("상품 이미지 조회 - 성공")
	void when_FindImage_Then_Success_HTTP_CODE_200() throws Exception {
		// This Method Fine Local Directory Images
		// Local Directory information is see application.properties
		mockMvc.perform(get(IMAGE_API + "/london.jpg"))
			.andExpect(status().isOk());
	}
}