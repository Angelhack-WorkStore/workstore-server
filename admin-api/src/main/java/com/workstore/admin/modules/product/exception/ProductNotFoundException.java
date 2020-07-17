package com.workstore.admin.modules.product.exception;

import com.workstore.admin.modules.common.error.ErrorCode;
import com.workstore.admin.modules.common.exception.BusinessException;

public class ProductNotFoundException extends BusinessException {
	public ProductNotFoundException() {
		super(ErrorCode.NOT_FOUND);
	}
}
