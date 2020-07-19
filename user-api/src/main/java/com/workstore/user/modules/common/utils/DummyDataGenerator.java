package com.workstore.user.modules.common.utils;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.workstore.common.modules.common.domain.Address;
import com.workstore.common.modules.common.domain.Association;
import com.workstore.common.modules.common.domain.Money;
import com.workstore.common.modules.product.domain.CautionNotes;
import com.workstore.common.modules.product.domain.Facility;
import com.workstore.common.modules.product.domain.HostInfo;
import com.workstore.common.modules.product.domain.ManageInfo;
import com.workstore.common.modules.product.domain.ManageType;
import com.workstore.common.modules.product.domain.PriceType;
import com.workstore.common.modules.product.domain.Product;
import com.workstore.common.modules.product.domain.ProductRepository;
import com.workstore.common.modules.product.domain.SeatInfo;
import com.workstore.common.modules.product.domain.SeatType;
import com.workstore.common.modules.product.domain.SubscribePrice;
import com.workstore.common.modules.tag.domain.Tag;

@Component
public class DummyDataGenerator implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void run(String... args) throws Exception {
		productRepository.saveAll(List.of(sampleOne().build(), sampleTwo().build(), sampleThree().build()));
	}

	public static Product.ProductBuilder sampleOne() {
		return Product.builder()
			.ownerId(new Association<>(1L))
			.name("[홍대] 코너스톤스페이스 지정석")
			.description("홍대에 있는 빈티지 엔틱분위기의 코너스톤스페이스 지정석")
			.content("1, 2층으로 나뉘어져 있으며, 대관시 2층의 엔틱, 빈티지 분위기의 유니크한 인테리어를"
				+ " 이용하여 각종 행사 / 전시 / 코워킹스페이스 등을 진행하고 있습니다. 2층 단독 화장실도 있기 때문에 장소를 이용하기에 방해 없이 즐기실 수 있습니다.")
			.likeCount(5)
			.address(sampleAddress().build())
			.seatInfo(sampleSeatInfo().build())
			.hostInfo(sampleHostInfo().build())
			.prices(samplePricesOne())
			.cautionNotes(sampleCautionNotes())
			.amenities(sampleAmenitiesOne())
			.manageInfo(sampleManageInfo())
			.tags(sampleTagsOne())
			;
	}

	public static Product.ProductBuilder sampleTwo() {
		return Product.builder()
			.ownerId(new Association<>(2L))
			.name("이든비즈플러스신논현 코워킹스페이스")
			.description("비즈니스센터 많아서 고민이시죠? 더 헤매지 마세요")
			.content("안녕하세요! 국내 1위 합리적인 가격과 프리미엄 서비스 대표님들과 함께 성장하는 (주)이든비즈 입니다! "
				+ "저희 이든비즈플러스에서는 다양한 업종의 입주사분들과 활발한 코워킹을 위해 노력하고 있으며,"
				+ " 프리랜서와 자택 근무자분들이 필요로 하는 서비스를 제공하기 위해 항상 노력하고 있습니다!")
			.likeCount(10)
			.address(sampleAddress().address1("서울특별시 강남구 논현동 203").address2("751 빌딩 8층").build())
			.seatInfo(sampleSeatInfo().seatCount(1).maxPersonnelCount(1).minPersonnelCount(1).build())
			.hostInfo(sampleHostInfo().build())
			.prices(samplePricesTwo())
			.cautionNotes(sampleCautionNotesOne())
			.amenities(sampleAmenitiesOne())
			.manageInfo(sampleManageInfo())
			.tags(sampleTagsTwo())
			;
	}

	public static Product.ProductBuilder sampleThree() {
		return Product.builder()
			.ownerId(new Association<>(3L))
			.name("벤처스페이스 오피스 1인-8인")
			.description("중소기업청 창업진흥원 지정 창업 비즈니스센터")
			.content("강남역 4번 출구에 위치한 정부 지정 창업 비즈니스센터로 유망한 스타트업 기업이 많이 입주하여 있습니다."
				+ "기술 멘토, 경영 멘토, 디자인 멘토, 세무 회계 특허 법률 등 다양한 멘토링을 받을 수 있습니다.")
			.likeCount(10)
			.address(sampleAddress().address1("서울특별시 강남구 강남대로 342").address2("역삼빌딩 5층").build())
			.seatInfo(sampleSeatInfo().minPersonnelCount(1).maxPersonnelCount(8).seatType(SeatType.FIX).seatCount(8).build())
			.hostInfo(sampleHostInfo().build())
			.prices(samplePricesThree())
			.cautionNotes(sampleCautionNotesOne())
			.amenities(sampleAmenitiesOne())
			.manageInfo(sampleManageInfo())
			.tags(sampleTagsThree())
			;
	}

	public static List<Facility> sampleAmenitiesOne() {
		return List.of(
			Facility.PRINTER,
			Facility.AIRCON,
			Facility.FAX,
			Facility.NO_SMOKE,
			Facility.WIFI,
			Facility.PUBLIC_ROUNGE,
			Facility.TABLE,
			Facility.PARKING,
			Facility.WHITEBOARD
		);
	}

	public static Address.AddressBuilder sampleAddress() {
		return Address.builder()
			.address1("서울특별시 마포구 와우산로 22길 34")
			.address2("자유인들 1층, 2층")
			.zipCode("233-12");
	}

	public static SeatInfo.SeatInfoBuilder sampleSeatInfo() {
		return SeatInfo.builder()
			.seatCount(10)
			.maxPersonnelCount(10)
			.minPersonnelCount(1)
			.seatType(SeatType.FIX);
	}

	public static HostInfo.HostInfoBuilder sampleHostInfo() {
		return HostInfo.builder()
			.site("http://www.naver.com")
			.hostEmail("kitty@naver.com")
			.hostPhoneNumber("010-2551-2351");
	}

	public static Set<SubscribePrice> samplePricesOne() {
		return Set.of(
			SubscribePrice.builder()
				.priceType(PriceType.DAY)
				.price(Money.wons(15000))
				.minUsageDay(1)
				.maxUsageDay(30)
				.build()
		);
	}

	public static Set<SubscribePrice> samplePricesTwo() {
		return Set.of(
			SubscribePrice.builder()
				.priceType(PriceType.MONTH)
				.price(Money.wons(220000))
				.minUsageDay(1)
				.maxUsageDay(30)
				.build()
		);
	}

	public static Set<SubscribePrice> samplePricesThree() {
		return Set.of(
			SubscribePrice.builder()
				.priceType(PriceType.MONTH)
				.price(Money.wons(495000))
				.minUsageDay(1)
				.maxUsageDay(30)
				.build()
		);
	}

	public static List<CautionNotes> sampleCautionNotesOne() {
		return List.of(
			new CautionNotes("24시간 이용 가능합니다."),
			new CautionNotes("원두커피, 티, 복합기, 와이파이, 무료사용 가능합니다."),
			new CautionNotes("별도의 문의사항은 연락처로 연락주세요.")
		);
	}

	public static List<CautionNotes> sampleCautionNotes() {
		return List.of(
			new CautionNotes("24시간 이용 가능합니다."),
			new CautionNotes("별도의 문의사항은 연락처로 연락주세요.")
		);
	}

	public static Set<Tag> sampleTagsOne() {
		return Set.of(
			new Tag("엔틱"),
			new Tag("카페"),
			new Tag("코워킹스페이스")
		);
	}

	public static Set<Tag> sampleTagsTwo() {
		return Set.of(
			new Tag("소호사무실"),
			new Tag("비즈니스센터"),
			new Tag("비상주사무실"),
			new Tag("강남사무실"),
			new Tag("신논현사무실")
		);
	}

	public static Set<Tag> sampleTagsThree() {
		return Set.of(
			new Tag("강남역"),
			new Tag("1인사무실"),
			new Tag("코워킹스페이스"),
			new Tag("독립사무실"),
			new Tag("비즈니스센터")
		);
	}

	public static Set<ManageInfo> sampleManageInfo() {
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
