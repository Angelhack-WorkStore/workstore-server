package com.workstore.common.modules.coupon.domain;

import java.util.UUID;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.workstore.common.modules.common.domain.Money;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @NoArgsConstructor
@Getter @DiscriminatorValue("AMOUNT")
public class AmountCoupon extends Coupon {
	private Money amount;

	public AmountCoupon(Long productId, String name, Money amount) {
		super(productId, UUID.randomUUID().toString(), name);
		this.amount = amount;
	}
}
