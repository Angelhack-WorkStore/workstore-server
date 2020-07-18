package com.workstore.admin.modules.product.api.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class FilePayload {
	private MultipartFile image;
	private String imageType;
}
