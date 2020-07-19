package com.workstore.common.modules.reservation.domain.event;

import com.workstore.common.modules.account.domain.Account;
import com.workstore.common.modules.reservation.domain.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data @AllArgsConstructor @Getter
public class ReservationCompletedEvent {
	private final Reservation reservation;
	private final Account account;
}
