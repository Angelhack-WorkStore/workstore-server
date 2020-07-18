package com.workstore.user.modules.reservation.api.request;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data @Builder
@AllArgsConstructor
public class ReservationLineItemPayload {
	private Long productId;
	private int quantity;
	private int price;
	private List<LocalDate> usageDates;
}
