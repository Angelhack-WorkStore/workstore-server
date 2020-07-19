package com.workstore.user.modules.pay.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.workstore.common.modules.account.domain.Account;
import com.workstore.common.modules.account.domain.AccountRepository;
import com.workstore.common.modules.product.domain.Product;
import com.workstore.common.modules.product.domain.ProductRepository;
import com.workstore.common.modules.reservation.domain.Reservation;
import com.workstore.common.modules.reservation.domain.ReservationRepository;
import com.workstore.user.infra.config.UserAppProperties;
import com.workstore.user.modules.account.exception.AccountNotFoundException;
import com.workstore.user.modules.pay.api.response.KakaoPayApprovalResponse;
import com.workstore.user.modules.pay.api.response.KakaoPayReadyResponse;
import com.workstore.user.modules.reservation.exception.ReservationNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PayService {
	private static final String HOST = "https://kapi.kakao.com";
	private KakaoPayReadyResponse response;
	private final ReservationRepository reservationRepository;
	private final AccountRepository accountRepository;
	private final ProductRepository productRepository;
	private final UserAppProperties appProperties;

	public KakaoPayReadyResponse prepare(Long reservationId, Long accountId) {
		Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(ReservationNotFoundException::new);
		Account account = accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
		Product product = productRepository.findById(reservation.getProductId()).orElseThrow(IllegalArgumentException::new);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = setKakaoApiHeaders();
		MultiValueMap<String, String> params = setPreparePayment(reservationId, reservation, account, product);
		HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);
		try {
			response = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"),
				body, KakaoPayReadyResponse.class);
			return response;
		}  catch (RestClientException | URISyntaxException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public KakaoPayApprovalResponse getSuccessInfo(Long reservationId, String pgToken, Account account) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = setKakaoApiHeaders();
		Reservation reservation = reservationRepository.findById(reservationId)
			.orElseThrow(ReservationNotFoundException::new);
		MultiValueMap<String, String> params = setRequestPayment(reservationId, pgToken, account, reservation);
		HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);

		try {
			return restTemplate.postForObject(
				new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalResponse.class);
		} catch (RestClientException | URISyntaxException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	private MultiValueMap<String, String> setRequestPayment(Long reservationId, String pgToken, Account account,
		Reservation reservation) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("cid", "TC0ONETIME");
		params.add("tid", response.getTid());
		params.add("partner_order_id", String.valueOf(reservationId));
		params.add("partner_user_id", account.getNickname());
		params.add("pg_token", pgToken);
		params.add("total_amount", reservation.getTotalAmount().longValue().toString());
		return params;
	}

	private MultiValueMap<String, String> setPreparePayment(Long reservationId, Reservation reservation, Account account,
		Product product) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("cid", "TC0ONETIME");
		params.add("partner_order_id", String.valueOf(reservationId));
		params.add("partner_user_id", account.getNickname());
		params.add("item_name", product.getName());
		params.add("quantity", reservation.getQuantity().toString());
		params.add("total_amount", reservation.getTotalAmount().longValue().toString());
		params.add("tax_free_amount", "5500");
		params.add("approval_url", "http://localhost:8081/api/pay/kakaoPaySuccess?reservation=" + reservationId);
		params.add("cancel_url", "http://localhost:3001/kakaoPayCancel");
		params.add("fail_url", "http://localhost:3001/kakaoPaySuccessFail");
		return params;
	}

	private HttpHeaders setKakaoApiHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK " +
			appProperties.getKakaoPay().getAdminKey());
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
		return headers;
	}
}
