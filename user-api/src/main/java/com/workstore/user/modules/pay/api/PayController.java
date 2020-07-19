package com.workstore.user.modules.pay.api;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.workstore.common.modules.account.domain.Account;
import com.workstore.user.infra.security.CurrentUser;
import com.workstore.user.modules.pay.api.response.KakaoPayApprovalResponse;
import com.workstore.user.modules.pay.service.PayService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pay")
@RequiredArgsConstructor
public class PayController {
	private final PayService payService;

	@GetMapping("/kakaoPaySuccess")
	public ResponseEntity kakaoPaySuccess(@RequestParam("reservation") Long reservationId,
		@RequestParam("pg_token") String pgToken, @CurrentUser Account account) throws URISyntaxException {
		KakaoPayApprovalResponse response = payService.getSuccessInfo(reservationId, pgToken, account);
		URI redirectUri = new URI("http://localhost:3001/reservationSuccess");
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(redirectUri);
		return new ResponseEntity<>(response, headers, HttpStatus.SEE_OTHER);
	}
}
