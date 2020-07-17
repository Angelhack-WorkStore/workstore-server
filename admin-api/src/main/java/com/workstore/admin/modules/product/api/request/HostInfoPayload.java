package com.workstore.admin.modules.product.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data @AllArgsConstructor @Builder
public class HostInfoPayload {
	private String hostEmail;
	private String hostPhoneNumber;
}
