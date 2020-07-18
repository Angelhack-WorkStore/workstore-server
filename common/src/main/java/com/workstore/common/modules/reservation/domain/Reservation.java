package com.workstore.common.modules.reservation.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import org.springframework.data.domain.AbstractAggregateRoot;

import com.workstore.common.modules.common.domain.Money;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Where(clause = "deleted = 0")
@Table(name = "RESERVATIONS")
@DynamicUpdate @DynamicInsert
@Entity @NoArgsConstructor @Getter
public class Reservation extends AbstractAggregateRoot<Reservation> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Embedded
	private ReservationUser user;		// 예약자
	private int personnelCount = 1;		// 예약 인원 수
	private Money totalAmount;			// 예약 최종 가격
	@Enumerated(EnumType.STRING)
	private ReservationStatus status;	// 예약 상태
	private LocalDateTime reservedAt;	// 예약 날짜
	private LocalDateTime modifiedAt;	// 예약 정보 수정 날짜
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
	@JoinColumn(name = "reservation_id")
	private List<ReservationLineItem> lineItems = new ArrayList<>();	// 예약 항목
	private int deleted = 0;

	private Reservation(ReservationUser user, int personnelCount, List<ReservationLineItem> lineItems) {
		this.user = user;
		this.personnelCount = personnelCount;
		this.status = ReservationStatus.PAYMENT_WAITING;
		this.lineItems = lineItems;
		this.totalAmount = calculateTotalAmounts();
	}

	private Money calculateTotalAmounts() {
		return Money.sum(lineItems, ReservationLineItem::getAmounts).times(this.personnelCount);
	}

	public static Reservation reserve(ReservationUser user, int personnelCount, List<ReservationLineItem> lineItems) {
		return new Reservation(user, personnelCount, lineItems);
	}

	public void pay() {
		this.status = ReservationStatus.RESERVED;
		this.reservedAt = LocalDateTime.now();
		this.modifiedAt = LocalDateTime.now();
	}

	public void cancel() {
		verifyStatus();
		this.status = ReservationStatus.CANCELED;
		this.modifiedAt = LocalDateTime.now();
		this.deleted = 1;
	}

	public Long getProductId() {
		return this.lineItems.get(0).getProductId();
	}

	public Integer getQuantity() {
		return this.lineItems.get(0).getQuantity();
	}

	private void verifyStatus() {
		if(this.status != ReservationStatus.RESERVED) {
			throw new IllegalStateException("예매 취소 가능한 상태가 아닙니다.");
		}
	}
}
