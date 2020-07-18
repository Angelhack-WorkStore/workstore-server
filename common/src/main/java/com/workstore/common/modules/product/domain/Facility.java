package com.workstore.common.modules.product.domain;

import lombok.Getter;

@Getter
public enum Facility {
	WIFI("인터넷/와이파이"),
	TV("TV/프로젝터"),
	WHITEBOARD("화이트보드"),
	SOUND("음향/마이크"),
	COOKING("취사시설"),
	PARKING("주차"),
	NO_SMOKE("금연"),
	DRESS_ROOM("탈의실"),
	ROOFTOP("테라스/루프탑"),
	PUBLIC_ROUNGE("공용라운지"),
	MIRROR("전신거울"),
	BARBECUE("바베큐시설"),
	FOOD("음식물반입가능"),
	BEVERAGES("주류반입가능"),
	PC("PC/노트북"),
	SHOWER("샤워시설"),
	TABLE("의자/테이블"),
	INTERNAL_TOILET("내부화장실"),
	AIRCON("에어컨"),
	CAFETERIA("카페 및 레스토랑"),
	PRINTER("인쇄/복사기"),
	PET("반려동물 동반가능"),
	FAX("팩스");

	private String name;

	Facility(String name) {
		this.name = name;
	}
}
