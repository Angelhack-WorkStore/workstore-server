package com.workstore.admin.modules.account.exception;

import com.workstore.admin.modules.common.error.ErrorCode;
import com.workstore.admin.modules.common.exception.BusinessException;

public class EmailTokenInvalidException extends BusinessException {
	public EmailTokenInvalidException() {
		super(ErrorCode.EMAIL_TOKEN_INVALID);
	}
}
