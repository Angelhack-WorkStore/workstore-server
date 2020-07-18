package com.workstore.admin.modules.product.service.factory;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.workstore.admin.modules.product.api.request.AddressPayload;
import com.workstore.admin.modules.product.api.request.CautionNotePayload;
import com.workstore.admin.modules.product.api.request.HostInfoPayload;
import com.workstore.admin.modules.product.api.request.ImagePayload;
import com.workstore.admin.modules.product.api.request.ManageInfoPayload;
import com.workstore.admin.modules.product.api.request.ProductPayload;
import com.workstore.admin.modules.product.api.request.SeatInfoPayload;
import com.workstore.admin.modules.product.api.request.SubscribePayload;
import com.workstore.common.modules.common.domain.Address;
import com.workstore.common.modules.common.domain.Association;
import com.workstore.common.modules.common.domain.Money;
import com.workstore.common.modules.image.domain.Image;
import com.workstore.common.modules.image.domain.ImageType;
import com.workstore.common.modules.product.domain.CautionNotes;
import com.workstore.common.modules.product.domain.Facility;
import com.workstore.common.modules.product.domain.HostInfo;
import com.workstore.common.modules.product.domain.ManageInfo;
import com.workstore.common.modules.product.domain.ManageType;
import com.workstore.common.modules.product.domain.PriceType;
import com.workstore.common.modules.product.domain.Product;
import com.workstore.common.modules.product.domain.SeatInfo;
import com.workstore.common.modules.product.domain.SeatType;
import com.workstore.common.modules.product.domain.SubscribePrice;
import com.workstore.common.modules.tag.domain.Tag;

@Component
public class ProductMaker {

	public Product make(Long ownerId, ProductPayload payload) {
		Product product = make(payload);
		product.setOwnerId(new Association<>(ownerId));
		return product;
	}

	public Product make(ProductPayload payload) {
		//Set<Image> images = manager.upload(payload.getImages());
		Set<SubscribePrice> prices = convertSubscribePrices(payload);
		Set<ManageInfo> manageInfos = convertManageInfo(payload);
		List<Facility> amenities = convertAmenities(payload);
		List<CautionNotes> cautionNotes = convertCautionNotes(payload);
		Set<Tag> tags = convertTags(payload);
		Address address = convertAddress(payload.getAddress());
		SeatInfo seatInfo = convertSeatInfo(payload.getSeatInfo());
		HostInfo hostInfo = convertHostInfo(payload.getHostInfo());
		return Product.builder()
			.name(payload.getName())
			.description(payload.getDescription())
			.content(payload.getContent())
			.likeCount(0)
			.hostInfo(hostInfo)
			.seatInfo(seatInfo)
			.address(address)
			.amenities(amenities)
			.manageInfo(manageInfos)
			.cautionNotes(cautionNotes)
			.prices(prices)
			.tags(tags)
			//.images(images)
			.build();
	}

	private List<CautionNotes> convertCautionNotes(ProductPayload payload) {
		List<CautionNotes> cautionNotes = new ArrayList<>();
		for (CautionNotePayload each : payload.getCautionNotes()) {
			cautionNotes.add(new CautionNotes(each.getCaution()));
		}
		return cautionNotes;
	}

	private HostInfo convertHostInfo(HostInfoPayload hostInfo) {
		return HostInfo.builder()
			.site(hostInfo.getSite())
			.hostEmail(hostInfo.getHostEmail())
			.hostPhoneNumber(hostInfo.getHostPhoneNumber())
			.build();
	}

	private SeatInfo convertSeatInfo(SeatInfoPayload seatInfo) {
		return SeatInfo.builder()
			.seatCount(seatInfo.getSeatCount())
			.minPersonnelCount(seatInfo.getMinPersonnelCount())
			.maxPersonnelCount(seatInfo.getMaxPersonnelCount())
			.seatType(SeatType.valueOf(seatInfo.getSeatType()))
			.build();
	}

	private Address convertAddress(AddressPayload address) {
		return Address.builder()
			.zipCode(address.getZipCode())
			.address1(address.getAddress1())
			.address2(address.getAddress2())
			.build();
	}

	private Set<Tag> convertTags(ProductPayload payload) {
		Set<Tag> tags = new HashSet<>();
		for(String each : payload.getTags()) {
			tags.add(new Tag(each));
		}
		return tags;
	}

	private List<Facility> convertAmenities(ProductPayload payload) {
		List<Facility> amenities = new ArrayList<>();
		for(String each : payload.getAmenities()) {
			amenities.add(Facility.valueOf(each));
		}
		return amenities;
	}

	private Set<ManageInfo> convertManageInfo(ProductPayload payload) {
		Set<ManageInfo> manageInfos = new HashSet<>();
		for(ManageInfoPayload each : payload.getManageInfos()) {
			manageInfos.add(ManageInfo.builder()
				.dayOfWeek(DayOfWeek.of(each.getDayOfWeek()))
				.startTime(each.getStartTime())
				.endTime(each.getEndTime())
				.manageType(ManageType.valueOf(each.getManageType()))
				.build());
		}
		return manageInfos;
	}

	private Set<SubscribePrice> convertSubscribePrices(ProductPayload payload) {
		Set<SubscribePrice> prices = new HashSet<>();
		for(SubscribePayload each : payload.getPrices()) {
			prices.add(SubscribePrice.builder()
				.price(Money.wons(each.getPrice()))
				.priceType(PriceType.valueOf(each.getType()))
				.build());
		}
		return prices;
	}
}
