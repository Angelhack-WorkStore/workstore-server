package com.workstore.common.modules.product.domain;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder @AllArgsConstructor
@Embeddable @NoArgsConstructor
public class HostInfo {
	private String site;				// 호스트 웹 사이트
	private String hostEmail;		// 호스트 이메일
	private String hostPhoneNumber;		// 호스트 전화번호
}
