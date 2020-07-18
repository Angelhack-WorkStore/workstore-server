package com.workstore.user.modules.reservation.service.factory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.workstore.common.modules.common.domain.Money;
import com.workstore.common.modules.reservation.domain.Reservation;
import com.workstore.common.modules.reservation.domain.ReservationLineItem;
import com.workstore.common.modules.reservation.domain.ReservationUser;
import com.workstore.user.modules.reservation.api.request.ReservationLineItemPayload;
import com.workstore.user.modules.reservation.api.request.ReservationPayload;
import com.workstore.user.modules.reservation.api.request.ReservationUserPayload;

@Component
public class ReservationMaker {
	public Reservation reserve(Long accountId, ReservationPayload payload) {
		List<ReservationLineItem> lineItems = convertLineItems(payload);
		ReservationUser user = convertUser(accountId, payload.getUsers());
		return Reservation.reserve(user, payload.getPersonnelCount(), lineItems);
	}

	private ReservationUser convertUser(Long accountId, ReservationUserPayload payload) {
		return ReservationUser.builder()
			.accountId(accountId)
			.name(payload.getName())
			.email(payload.getEmail())
			.phoneNumber(payload.getPhoneNumber())
			.purposeOfUse(payload.getPurposeOfUse())
			.requirement(payload.getRequirement())
			.build();
	}

	private List<ReservationLineItem> convertLineItems(ReservationPayload payload) {
		List<ReservationLineItem> lineItems = new ArrayList<>();
		for(ReservationLineItemPayload each : payload.getLineItems()) {
			lineItems.add(ReservationLineItem.builder()
				.productId(each.getProductId())
				.quantity(each.getQuantity())
				.price(Money.wons(each.getPrice()))
				.usageDates(each.getUsageDates())
				.build());
		}
		return lineItems;
	}
}
