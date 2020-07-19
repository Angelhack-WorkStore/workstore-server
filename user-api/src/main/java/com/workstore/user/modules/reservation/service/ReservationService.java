package com.workstore.user.modules.reservation.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.workstore.common.modules.account.domain.Account;
import com.workstore.common.modules.reservation.domain.Reservation;
import com.workstore.common.modules.reservation.domain.ReservationRepository;
import com.workstore.user.modules.reservation.api.request.ReservationPayload;
import com.workstore.user.modules.reservation.exception.ReservationNotFoundException;
import com.workstore.user.modules.reservation.service.component.ReservationMaker;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {
	private final ReservationRepository reservationRepository;
	private final ReservationMaker reservationMaker;

	public Reservation reserve(Long accountId, ReservationPayload payload) {
		Reservation reservation = reservationMaker.reserve(accountId, payload);
		return this.reservationRepository.save(reservation);
	}

	public Reservation paymentComplete(Long reservationId, Account account) {
		Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(
			ReservationNotFoundException::new);
		reservation.paymentComplete(account);
		return reservation;
	}
}
