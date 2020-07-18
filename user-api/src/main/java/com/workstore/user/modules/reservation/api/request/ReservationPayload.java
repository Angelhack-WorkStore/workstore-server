package com.workstore.user.modules.reservation.api.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data @Builder
@AllArgsConstructor
public class ReservationPayload {
	private ReservationUserPayload users;
	private int personnelCount;
	private List<ReservationLineItemPayload> lineItems;
}
