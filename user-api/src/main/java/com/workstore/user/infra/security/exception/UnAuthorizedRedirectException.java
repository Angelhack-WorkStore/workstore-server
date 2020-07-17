package com.workstore.user.infra.security.exception;

import com.workstore.user.modules.common.error.ErrorCode;
import com.workstore.user.modules.common.exception.BusinessException;

public class UnAuthorizedRedirectException extends BusinessException {
	public UnAuthorizedRedirectException(String message) {
		super(message, ErrorCode.UN_AUTHORIZED);
	}
}
