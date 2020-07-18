package com.workstore.admin.modules.product.api.response;

import com.workstore.admin.modules.product.api.request.ImagePayload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class UploadResponse {
	private ImagePayload image;
}

