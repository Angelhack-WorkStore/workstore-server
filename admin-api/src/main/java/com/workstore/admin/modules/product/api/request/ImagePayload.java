package com.workstore.admin.modules.product.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class ImagePayload {
	private String fileName;
	//private String filePath;
	private String imageType;

	public ImagePayload(String fileName, String imageType) {
		this.fileName = fileName;
		this.imageType = imageType;
	}
}
