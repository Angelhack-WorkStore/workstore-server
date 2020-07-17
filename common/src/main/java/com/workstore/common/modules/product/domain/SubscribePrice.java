package com.workstore.common.modules.product.domain;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.workstore.common.modules.common.domain.Money;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder @AllArgsConstructor
@Embeddable @NoArgsConstructor
public class SubscribePrice {
	private Money price;			// 공간 가격
	@Enumerated(EnumType.STRING)
	private PriceType priceType;	// 일 단위, 월 단위
	private int minUsageDay;		// 최소 예매 날짜 단위
	private int maxUsageDay;		// 최대 예매 날짜 단위
}
