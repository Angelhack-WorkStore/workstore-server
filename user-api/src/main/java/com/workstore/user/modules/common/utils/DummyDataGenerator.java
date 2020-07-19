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
			.name("슈가맨워크 상동1호점 자유석")
			.description("온라인기반 사업을 영위하는 분들의 최적화된 공간.")
			.content("‘공간이 우리를 움직입니다’\n"
				+ "국내 유일의 공간심리학 기반 공유사무실 슈가맨워크의 첫 번째 공간!\n"
				+ "입주자들의 생산성과 효율성을 높이고 창의적인 아이디어가 잘 떠오를 수 있도록 공간을 디자인했습니다!\n"
				+ "\n"
				+ "입주자분들은 슈가맨워크가 제공하는 다양한 편의시설 및 비즈니스 혜택을 누릴 수 있습니다. 창업 초기에 어려움을 겪고 있는 입주자들을 위한 브랜딩 서비스도 있어요!\n"
				+ "\n"
				+ "- 브랜딩 서비스 (개발, 디자인, 마케팅)\n"
				+ "- 24/7 자유로운 이용\n"
				+ "- 무료 원두커피 및 회의실 사용 가능\n"
				+ "- 자연채광 스튜디오\n"
				+ "- 무료 복합기 (프린트,팩스,스캔)\n"
				+ "- 티타임, 소모임 등 각종 네트워킹 프로그램\n"
				+ "- 무료 강연 및 세무상담 지원\n"
				+ "- 슈가맨워크 제휴사 할인\n"
				+ "\n"
				+ "창업 초기, 부담 없는 공간을 찾으시는 분들은\n"
				+ "가성비, 가심비 모두 높은 슈가맨워크와 함께 하세요!")
			.likeCount(5)
			.address(sampleAddress().build())
			.seatInfo(sampleSeatInfo().build())
			.hostInfo(sampleHostInfo().build())
			.prices(samplePricesOne())
			.cautionNotes(sampleCautionNotesOne())
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
			.content("비즈니스센터가 많아서 고민이시죠? 더 이상 헤매지 마세요!\n"
				+ "\n"
				+ "안녕하세요! 국내 1위 합리적인 가격과 프리미엄 서비스\n"
				+ "대표님들과 함께 성장하는 (주)이든비즈 입니다!\n"
				+ "\n"
				+ "저희 이든비즈플러스에서는 다양한 업종의 입주사분들과\n"
				+ "활발한 코워킹을 위해 노력하고 있으며,\n"
				+ "\n"
				+ "프리랜서와 자택 근무자분들이 필요로 하는 서비스가 무엇인지\n"
				+ "(주)이든비즈만의 오랜 경험을 바탕으로 타 브랜드에서 받아보시지 못한\n"
				+ "다양한 서비스뿐만 아니라 업무지원 서비스까지 받아보실 수 있습니다!\n"
				+ "\n"
				+ "또한!\n"
				+ "불필요한 지출과 시간을 절약하기 위해\n"
				+ "카페나 별도의 회의 공간을 대여하지 않고 이용하실 수 있도록\n"
				+ "차별화 된 회의실, 넓은 카페라운지 공간에\n"
				+ "원두커피와 다양한 차 종류를 준비하였습니다.\n"
				+ "\n"
				+ "더 이상 헤매지 마세요!\n"
				+ "\n"
				+ "편안한 마음으로 문의 하셔서\n"
				+ "(주)이든비즈만의 프리미엄 서비스를 받아보시길 바랍니다.\n"
				+ "저희 (주)이든비즈를 찾아봐 주셔서 감사합니다.\n"
				+ "\n"
				+ "- 자세한 내용은 리셉션 서비스 시간에 상담을 해주시기 바랍니다.")
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
			.name("코너스톤스페이스 지정석")
			.description("캄(Calm)워킹스페이스, 코너스톤스페이스")
			.content("[오피스 설명]\n"
				+ "- 층별 지문인식, 오피스 개별 도어락으로 최고의 보안을 갖춘 오피스\n"
				+ "- 1년 365일 24시간 언제나 사용할 수 있는 공간\n"
				+ "- 개별 주말포함 24시간 냉·난방으로 쾌적한 업무환경 제공\n"
				+ "- Casamia와 콜라보레이션한 1400mm*700mm의 넓은 책상\n"
				+ "- 장시간 근무에도 편안한 인체공학설계 의자 Herman Miller & Vitra\n"
				+ "- 창의적 사고가 가능한 2.7m의 높은 천장\n"
				+ "\n"
				+ "[공간 설명]\n"
				+ "- 한남대교 남단, 신사역 1분 거리 비즈니스 최적의 위치\n"
				+ "\n"
				+ "[제공 서비스]\n"
				+ "- 60명 수용 가능한 Lounge Hall / 화상회의&프레젠테이션 시설이 갖춰진 컨퍼런스룸 / 1:1 소형 미팅룸\n"
				+ "- 메일룸 / 폰부스 / OA룸\n"
				+ "\n"
				+ "[기타시설]\n"
				+ "- Kornerstone Cafe Lounge 할인혜택\n"
				+ "- 에스프레소머신 및 케이터링 가능한 프리미엄 캔틴\n"
				+ "- 6F Lounge Hall과 연결된 하늘정원\n"
				+ "- 발렛파킹 서비스")
			.likeCount(8)
			.address(sampleAddress().address1("서울특별시 강남구 강남대로 624").address2("신사역 ICT타워 6층").build())
			.seatInfo(sampleSeatInfo().minPersonnelCount(1).maxPersonnelCount(5).seatType(SeatType.FIX).seatCount(5).build())
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
			.address1("경기도 부천시 상동 533-1")
			.address2("드라마시티 702호")
			.zipCode("233-12");
	}

	public static SeatInfo.SeatInfoBuilder sampleSeatInfo() {
		return SeatInfo.builder()
			.seatCount(4)
			.maxPersonnelCount(4)
			.minPersonnelCount(1)
			.seatType(SeatType.FREE);
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

	public static Set<Tag> sampleTagsOne() {
		return Set.of(
			new Tag("부천소호사무실"),
			new Tag("1인사무실"),
			new Tag("코워킹스페이스"),
			new Tag("소호사무실"),
			new Tag("쇼핑몰사무실")
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
			new Tag("신사역"),
			new Tag("고정석")
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
