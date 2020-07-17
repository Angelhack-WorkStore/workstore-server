package com.workstore.common.modules.product.domain;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Embeddable
@NoArgsConstructor @AllArgsConstructor
public class CautionNotes {
	private String caution;
}
