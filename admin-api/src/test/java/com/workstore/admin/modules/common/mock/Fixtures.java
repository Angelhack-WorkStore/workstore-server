package com.workstore.admin.modules.common.mock;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.workstore.admin.modules.product.api.request.AddressPayload;
import com.workstore.admin.modules.product.api.request.HostInfoPayload;
import com.workstore.admin.modules.product.api.request.ManageInfoPayload;
import com.workstore.admin.modules.product.api.request.MoneyPayload;
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

public class Fixtures {

	public static ProductPayload.ProductPayloadBuilder registerProductPayload() {
		Set<ManageInfoPayload> manageInfos = new HashSet<>();
		for (int i = 1; i <= 7; i++) {
			manageInfos.add(
				ManageInfoPayload.builder()
					.dayOfWeek(i)
					.startTime(LocalTime.of(9, 0, 0))
					.endTime(LocalTime.of(22, 0, 0))
					.manageType("OPERATE")
					.build()
			);
		}
		return ProductPayload.builder()
			.name("마이워크스페이스 3호점 -지정석")
			.description("마이 워크스페이스 좋다")
			.content("많이 와")
			.prices(Set.of(
				SubscribePayload.builder()
					.price(new MoneyPayload(22000))
					.type("DAY")
					.minUsageDay(1)
					.maxUsageDay(30)
					.build(),
				SubscribePayload.builder()
					.price(new MoneyPayload(660000))
					.type("MONTH")
					.minUsageDay(1)
					.maxUsageDay(60)
					.build()
			))
			.cautionNotes(cautionNotePayloads())
			.address(addressPayload().build())
			.seatInfo(seatInfoPayload().build())
			.hostInfo(hostInfoPayload().build())
			.amenities(List.of("WIFI", "PRINTER"))
			.manageInfos(manageInfos)
			.tags(List.of(
				"코워킹 스페이스",
				"뷰 맛집",
				"강남구 논현동"
			));
	}

	/*public static List<ImagePayload> imagePayloads() {
		List<ImagePayload> payloads = new ArrayList<>();
		for(int i=1; i<6; i++) {
			payloads.add(
				new ImagePayload(
					new MockMultipartFile("product-file", "fileName" + i + ".jpg", "image/jpg", new byte[213401]),
				"THUMBNAIL"));
		}
		return payloads;
	}*/

	public static List<String> cautionNotePayloads() {
		return List.of(
			"예약 시 유의사항 1",
			"예약 시 유의사항 2",
			"예약 시 유의사항 3",
			"예약 시 유의사항 4"
		);
	}

	private static HostInfoPayload.HostInfoPayloadBuilder hostInfoPayload() {
		return HostInfoPayload.builder()
			.site("http://www.naver.com")
			.hostEmail("rebwon@gmail.com")
			.hostPhoneNumber("010-2314-1235");
	}

	private static SeatInfoPayload.SeatInfoPayloadBuilder seatInfoPayload() {
		return SeatInfoPayload.builder()
			.seatCount(15)
			.maxPersonnelCount(15)
			.minPersonnelCount(1)
			.seatType("FREE");
	}

	private static AddressPayload.AddressPayloadBuilder addressPayload() {
		return AddressPayload.builder()
			.zipCode("123-41")
			.address1("서울시 강남구 논현동")
			.address2("빌지워터 빌딩 3층");
	}

	public static Product.ProductBuilder product() {
		return Product.builder()
			.ownerId(new Association<>(1L))
			.name("슈가맨워크 상동2호점 자유석")
			.description("온라인 기반으로 사업을 영위하는 창업자들의 공간")
			.content("공간 소개란")
			.likeCount(0)
			.address(address().build())
			.seatInfo(seatInfo().build())
			.hostInfo(hostInfo().build())
			.prices(prices())
			.cautionNotes(cautionNotes())
			.amenities(amenities())
			.manageInfo(manageInfos())
			.tags(tags())
			.images(images());
	}

	public static List<CautionNotes> cautionNotes() {
		return List.of(
			new CautionNotes("예약 시 유의사항 1"),
			new CautionNotes("예약 시 유의사항 2"),
			new CautionNotes("예약 시 유의사항 3"),
			new CautionNotes("예약 시 유의사항 4")
		);
	}

	public static Address.AddressBuilder address() {
		return Address.builder()
			.zipCode("231-12")
			.address1("경기도 고양시 덕양구")
			.address2("원흥동 123번길 12");
	}

	public static SeatInfo.SeatInfoBuilder seatInfo() {
		return SeatInfo.builder()
			.seatCount(15)
			.maxPersonnelCount(15)
			.minPersonnelCount(1)
			.seatType(SeatType.FREE);
	}

	public static HostInfo.HostInfoBuilder hostInfo() {
		return HostInfo.builder()
			.site("http://www.naver.com")
			.hostEmail("kitty@naver.com")
			.hostPhoneNumber("010-2551-2351");
	}

	public static Set<Tag> tags() {
		return Set.of(
			new Tag("코워킹 스페이스"),
			new Tag("자유석"),
			new Tag("깔끔한 분위기")
		);
	}

	public static Set<SubscribePrice> prices() {
		return Set.of(
			SubscribePrice.builder()
				.priceType(PriceType.MONTH)
				.price(Money.wons(550000))
				.minUsageDay(1)
				.maxUsageDay(60)
				.build(),
			SubscribePrice.builder()
				.priceType(PriceType.DAY)
				.price(Money.wons(18400))
				.minUsageDay(1)
				.maxUsageDay(30)
				.build()
		);
	}

	public static List<Facility> amenities() {
		return List.of(
			Facility.PRINTER,
			Facility.AIRCON,
			Facility.FAX
		);
	}

	public static Set<Image> images() {
		return Set.of(
			Image.builder()
				.fileName("main.jpg")
				.size(601231)
				.mimeType("image/jpg")
				.imageType(ImageType.MAIN)
				.createdAt(LocalDateTime.now())
				.build(),
			Image.builder()
				.fileName("thumb1.jpg")
				.size(701231)
				.mimeType("image/jpg")
				.imageType(ImageType.THUMBNAIL)
				.createdAt(LocalDateTime.now())
				.build(),
			Image.builder()
				.fileName("thumb2.jpg")
				.size(501231)
				.mimeType("image/jpg")
				.imageType(ImageType.THUMBNAIL)
				.createdAt(LocalDateTime.now())
				.build(),
			Image.builder()
				.fileName("thumb3.jpg")
				.size(801231)
				.mimeType("image/jpg")
				.imageType(ImageType.THUMBNAIL)
				.createdAt(LocalDateTime.now())
				.build()
		);
	}

	public static Set<ManageInfo> manageInfos() {
		return Set.of(
			ManageInfo.builder()
				.dayOfWeek(DayOfWeek.MONDAY)
				.startTime(LocalTime.of(9, 0, 0))
				.endTime(LocalTime.of(22, 0, 0))
				.manageType(ManageType.OPERATE)
				.build(),
			ManageInfo.builder()
				.dayOfWeek(DayOfWeek.TUESDAY)
				.startTime(LocalTime.of(9, 0, 0))
				.endTime(LocalTime.of(22, 0, 0))
				.manageType(ManageType.OPERATE)
				.build(),
			ManageInfo.builder()
				.dayOfWeek(DayOfWeek.WEDNESDAY)
				.startTime(LocalTime.of(9, 0, 0))
				.endTime(LocalTime.of(22, 0, 0))
				.manageType(ManageType.OPERATE)
				.build(),
			ManageInfo.builder()
				.dayOfWeek(DayOfWeek.THURSDAY)
				.startTime(LocalTime.of(9, 0, 0))
				.endTime(LocalTime.of(22, 0, 0))
				.manageType(ManageType.OPERATE)
				.build(),
			ManageInfo.builder()
				.dayOfWeek(DayOfWeek.FRIDAY)
				.startTime(LocalTime.of(9, 0, 0))
				.endTime(LocalTime.of(22, 0, 0))
				.manageType(ManageType.HOLIDAY)
				.build(),
			ManageInfo.builder()
				.dayOfWeek(DayOfWeek.SATURDAY)
				.startTime(LocalTime.of(9, 0, 0))
				.endTime(LocalTime.of(22, 0, 0))
				.manageType(ManageType.HOLIDAY)
				.build(),
			ManageInfo.builder()
				.dayOfWeek(DayOfWeek.SUNDAY)
				.startTime(LocalTime.of(9, 0, 0))
				.endTime(LocalTime.of(22, 0, 0))
				.manageType(ManageType.OPERATE)
				.build()
		);
	}
}
