package com.workstore.admin.modules.product.api.request;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ImagePayload {
	@JsonIgnore
	private MultipartFile file;
	@JsonProperty("file")
	private String fileName;
	private String imageType;
}
