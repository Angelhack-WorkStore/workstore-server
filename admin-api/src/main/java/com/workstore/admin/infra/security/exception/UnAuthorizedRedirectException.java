package com.workstore.admin.infra.security.exception;

import com.workstore.admin.modules.common.error.ErrorCode;
import com.workstore.admin.modules.common.exception.BusinessException;

public class UnAuthorizedRedirectException extends BusinessException {
	public UnAuthorizedRedirectException(String message) {
		super(message, ErrorCode.UN_AUTHORIZED);
	}
}
