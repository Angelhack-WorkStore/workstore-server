package com.workstore.user.modules.reservation.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workstore.common.modules.account.domain.Account;
import com.workstore.common.modules.reservation.domain.Reservation;
import com.workstore.user.infra.security.CurrentUser;
import com.workstore.user.modules.pay.api.response.KakaoPayReadyResponse;
import com.workstore.user.modules.pay.service.PayService;
import com.workstore.user.modules.reservation.api.request.ReservationPayload;
import com.workstore.user.modules.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {
	private final ReservationService service;
	private final PayService payService;

	@PostMapping
	public ResponseEntity reserve(@RequestBody ReservationPayload payload, @CurrentUser Account account) {
		Reservation reservation = service.reserve(account.getId(), payload);
		return ResponseEntity.ok(reservation);
	}

	@PostMapping("/{reservationId}/pay")
	public ResponseEntity preparedPayment(@PathVariable Long reservationId, @CurrentUser Account account) {
		KakaoPayReadyResponse readyResponse = payService.prepare(reservationId, account.getId());
		return null;
	}
}
