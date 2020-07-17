package com.workstore.common.modules.reservation.domain;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor @AllArgsConstructor
@Builder @Getter
public class ReservationUser {
	private Long accountId;				// 예약자 ID
	private String name;				// 예약자 이름
	private String email;				// 예약자 이메일
	private String phoneNumber;			// 예약자 전화번호
	private String purposeOfUse;		// 예약자 사용 목적
	private String requirement;			// 예약자 요구 사항
}
