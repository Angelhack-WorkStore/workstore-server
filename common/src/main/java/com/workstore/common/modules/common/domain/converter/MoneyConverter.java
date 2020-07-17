package com.workstore.common.modules.common.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.workstore.common.modules.common.domain.Money;

@Converter(autoApply = true)
public class MoneyConverter implements AttributeConverter<Money, Long> {
	@Override
	public Long convertToDatabaseColumn(Money amount) {
		return amount.getAmount().longValue();
	}

	@Override
	public Money convertToEntityAttribute(Long amount) {
		return Money.wons(amount);
	}
}