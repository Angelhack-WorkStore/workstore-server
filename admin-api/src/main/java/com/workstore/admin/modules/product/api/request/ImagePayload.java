package com.workstore.admin.modules.product.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ImagePayload {
	private String fileName;
	private String mimeType;
	private String filePath;
	private long size;
	private String imageType;

	public ImagePayload(String fileName, String mimeType, long size,
		String imageType) {
		this.fileName = fileName;
		this.mimeType = mimeType;
		this.size = size;
		this.imageType = imageType;
	}
}
