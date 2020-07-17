package com.workstore.common.modules.coupon.domain;

import java.util.UUID;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter @NoArgsConstructor
@DiscriminatorValue("PERCENT")
public class PercentCoupon extends Coupon {
	private double percent;

	public PercentCoupon(Long productId, String name, double percent) {
		super(productId, UUID.randomUUID().toString(), name);
		this.percent = percent;
	}
}
