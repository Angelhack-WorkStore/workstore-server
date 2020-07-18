package com.workstore.admin.modules.product.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data @AllArgsConstructor @Builder
public class SubscribePayload {
	private MoneyPayload price;
	private String type;
	private Integer minUsageDay;
	private Integer maxUsageDay;
}
