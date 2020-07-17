package com.workstore.common.modules.common.domain;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@Embeddable
@NoArgsConstructor
public class Address {
	private String zipCode;
	private String address1;
	private String address2;
}

