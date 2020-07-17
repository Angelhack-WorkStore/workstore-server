package com.workstore.common.modules.product.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;

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
public class ManageInfo {
	private DayOfWeek dayOfWeek;		// 요일
	private LocalTime startTime;		// 시작 시간
	private LocalTime endTime;			// 종료 시간
	@Enumerated(EnumType.STRING)
	private ManageType manageType;		// 이용 날짜 관리 타입
}
