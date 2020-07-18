package com.workstore.user.modules.common.mock;

import java.time.LocalDate;
import java.util.List;

import com.workstore.user.modules.reservation.api.request.ReservationLineItemPayload;
import com.workstore.user.modules.reservation.api.request.ReservationPayload;
import com.workstore.user.modules.reservation.api.request.ReservationUserPayload;

public class Fixtures {
	public static ReservationPayload registerReservationPayload() {
		return ReservationPayload.builder()
			.users(ReservationUserPayload.builder()
				.name("김철수")
				.email("chulsu@naver.com")
				.phoneNumber("010-3215-1235")
				.purposeOfUse("이용 목적 입력란")
				.requirement("예약자 요구 사항 입력란")
				.build())
			.personnelCount(3)
			.lineItems(List.of(
				ReservationLineItemPayload.builder()
					.productId(1L)
					.quantity(5)
					.price(25000)
					.usageDates(List.of(
						LocalDate.of(2020, 7, 16),
						LocalDate.of(2020, 7, 17),
						LocalDate.of(2020, 7, 18),
						LocalDate.of(2020, 7, 20),
						LocalDate.of(2020, 7, 21)
					))
					.build()
			))
			.build();
	}
}
