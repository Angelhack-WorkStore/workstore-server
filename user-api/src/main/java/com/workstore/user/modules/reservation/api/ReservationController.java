package com.workstore.user.modules.reservation.api;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ResponseEntity preparedPayment(@PathVariable Long reservationId, @CurrentUser Account account) throws
		URISyntaxException {
		KakaoPayReadyResponse readyResponse = payService.prepare(reservationId, account.getId());

		URI redirectUri = new URI(readyResponse.getNext_redirect_pc_url());
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(redirectUri);
		return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
	}

	@GetMapping("/{reservationId}/success")
	public ResponseEntity reserveSuccess(@PathVariable Long reservationId, @CurrentUser Account account) throws
		URISyntaxException {
		Reservation reservation = service.paymentComplete(reservationId, account);
		URI redirectUri = new URI("http://localhost:3001/myPage");
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(redirectUri);
		return new ResponseEntity<>(reservation, headers, HttpStatus.SEE_OTHER);
	}
}
