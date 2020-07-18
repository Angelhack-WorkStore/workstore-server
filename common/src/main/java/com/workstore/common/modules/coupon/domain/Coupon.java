package com.workstore.common.modules.coupon.domain;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@Inheritance @DiscriminatorColumn
public abstract class Coupon {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long productId;
	private String number;
	private String name;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;

	@PrePersist
	public void setCreateAt() {
		this.createdAt = LocalDateTime.now();
	}

	@PreUpdate
	public void setModifiedAt() {
		this.modifiedAt = LocalDateTime.now();
	}

	public Coupon(Long productId, String number, String name) {
		this.productId = productId;
		this.number = number;
		this.name = name;
	}
}
