package com.workstore.admin.modules.reservation.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workstore.admin.infra.security.CurrentUser;
import com.workstore.common.modules.account.domain.Account;
import com.workstore.common.modules.account.domain.AccountRepository;
import com.workstore.common.modules.product.domain.ProductRepository;
import com.workstore.common.modules.reservation.domain.ReservationRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReservationManageController {
	private final ProductRepository productRepository;
	private final AccountRepository accountRepository;
	private final ReservationRepository reservationRepository;

	@GetMapping("/api/admin/statistics")
	public ResponseEntity getProductStatistics(@CurrentUser Account account) {

		return null;
	}
}
