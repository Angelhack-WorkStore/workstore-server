package com.workstore.common.modules.product.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import com.workstore.common.modules.account.domain.Account;
import com.workstore.common.modules.common.domain.Address;
import com.workstore.common.modules.common.domain.Association;
import com.workstore.common.modules.image.domain.Image;
import com.workstore.common.modules.tag.domain.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "PRODUCTS")
@Entity @NoArgsConstructor @AllArgsConstructor
@Where(clause = "deleted = 0")
@DynamicInsert @DynamicUpdate
@EqualsAndHashCode @Getter @Setter @Builder
public class Product {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "OWNER_ID", nullable = false)
	private Association<Account> ownerId;
	private String name;				// 공간 상호명
	private String description;			// 공간 한줄 소개
	private String content;				// 공간 소개
	@Embedded
	private Address address;			// 공간 주소
	@ElementCollection
	private Set<SubscribePrice> prices = new HashSet<>();	// 구독 상품 가격 (일, 월 단위)
	private Integer likeCount;			// 좋아요 수
	@Embedded
	private SeatInfo seatInfo;			// 좌석 정보
	@Embedded
	private HostInfo hostInfo;			// 호스트 정보
	@ElementCollection
	private Set<ManageInfo> manageInfo = new HashSet<>();	// 공간 이용 관리 정보
	@ElementCollection
	private List<Facility> amenities = new ArrayList<>();	// 편의 시설
	@ElementCollection
	private List<CautionNotes> cautionNotes = new ArrayList<>();
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "product_id")
	private Set<Image> images = new HashSet<>();			// 상품 이미지
	private int deleted = 0;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "product_id")
	private Set<Tag> tags = new HashSet<>();

	public void update(Product request) {
		this.name = request.getName();
		this.description = request.getDescription();
		this.content = request.getContent();
		this.address = request.getAddress();
		this.prices = request.getPrices();
		this.seatInfo = request.getSeatInfo();
		this.hostInfo = request.getHostInfo();
		this.manageInfo = request.getManageInfo();
		this.amenities = request.getAmenities();
		this.cautionNotes = request.getCautionNotes();
		changeTags(request.getTags());
		//changeImages(request.getImages());
	}

	public void changeTags(Set<Tag> tags) {
		this.tags.clear();
		this.tags.addAll(tags);
	}

	public void changeImages(Set<Image> images) {
		this.images.clear();
		this.images.addAll(images);
	}

	public void remove() {
		this.deleted = 1;
	}
}
