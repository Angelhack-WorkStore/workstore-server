package com.workstore.common.modules.common.domain.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.workstore.common.modules.common.domain.Association;

@Converter(autoApply = true)
public class AssociationConverter implements AttributeConverter<Association<?>, Long> {
	@Override
	public Long convertToDatabaseColumn(Association<?> associationId) {
		return associationId.getId();
	}

	@Override
	public Association<?> convertToEntityAttribute(Long identifier) {
		return new Association<>(identifier);
	}
}
