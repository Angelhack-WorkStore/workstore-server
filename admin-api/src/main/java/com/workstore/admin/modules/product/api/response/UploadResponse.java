package com.workstore.admin.modules.product.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadResponse {
	private String fileName;
	private String fileStaticUri;
	private String fileType;
	private String imageType;
	private long size;
}

