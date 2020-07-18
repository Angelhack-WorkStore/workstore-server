package com.workstore.user.modules.reservation.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data @Builder
@AllArgsConstructor
public class ReservationUserPayload {
	private String name;
	private String email;
	private String phoneNumber;
	private String purposeOfUse;
	private String requirement;
}
