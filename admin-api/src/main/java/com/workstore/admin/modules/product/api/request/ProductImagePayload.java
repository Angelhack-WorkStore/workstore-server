package com.workstore.admin.modules.product.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data @Builder
@AllArgsConstructor
public class ProductImagePayload {
	private String fileName;
	private long size;
	private String mimeType;
	private String imageType;
}
