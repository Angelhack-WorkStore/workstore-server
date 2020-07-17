package com.workstore.common.modules.product.domain;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder @AllArgsConstructor
@Embeddable @NoArgsConstructor
public class SeatInfo {
	private int seatCount = 1;		// 좌석 수
	private int minPersonnelCount = 1;		// 최소 인원 수
	private int maxPersonnelCount = 1;		// 최대 인원 수
	@Enumerated(EnumType.STRING)
	private SeatType seatType;				// 좌석 타입
}
