package com.workstore.common.modules.product.domain;

public enum Facility {
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
