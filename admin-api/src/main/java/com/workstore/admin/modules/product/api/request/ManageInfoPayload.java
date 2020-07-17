package com.workstore.admin.modules.product.api.request;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data @AllArgsConstructor @Builder
public class ManageInfoPayload {
	private int dayOfWeek;
	private LocalTime startTime;
	private LocalTime endTime;
	private String manageType;
}
