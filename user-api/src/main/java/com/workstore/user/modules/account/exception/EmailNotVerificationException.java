package com.workstore.user.modules.account.exception;

import com.workstore.user.modules.common.error.ErrorCode;
import com.workstore.user.modules.common.exception.BusinessException;

public class EmailNotVerificationException extends BusinessException {
	public EmailNotVerificationException() {
		super(ErrorCode.EMAIL_NOT_VERIFIED);
	}
}
