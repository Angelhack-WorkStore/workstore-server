package com.workstore.common.modules.reservation.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.workstore.common.modules.common.domain.Money;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @NoArgsConstructor
public class ReservationLineItem {
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long productId;			// 상품 ID
	private Money price;			// 상품의 가격
	private Money amounts;			// 예약 항목의 가격
	private int quantity;			// 하루 당 1개
	@ElementCollection
	private List<LocalDate> usageDates = new ArrayList<>();

	@Builder
	public ReservationLineItem(Long productId, Money price, int quantity, List<LocalDate> usageDates) {
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
		this.usageDates = usageDates;
		this.amounts = calculateAmounts();
	}

	private Money calculateAmounts() {
		return price.times(quantity);
	}
}
