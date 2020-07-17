package com.workstore.user.modules.account.exception;

import com.workstore.user.modules.common.error.ErrorCode;
import com.workstore.user.modules.common.exception.BusinessException;

public class EmailTokenInvalidException extends BusinessException {
	public EmailTokenInvalidException() {
		super(ErrorCode.EMAIL_TOKEN_INVALID);
	}
}
