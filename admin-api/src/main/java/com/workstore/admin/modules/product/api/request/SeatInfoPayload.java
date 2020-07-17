package com.workstore.admin.modules.product.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data @Builder
@AllArgsConstructor
public class SeatInfoPayload {
	private int seatCount;
	private int minPersonnelCount;
	private int maxPersonnelCount;
	private String seatType;
}
