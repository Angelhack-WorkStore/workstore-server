package com.workstore.admin.modules.account.exception;

import com.workstore.admin.modules.common.error.ErrorCode;
import com.workstore.admin.modules.common.exception.BusinessException;

public class EmailNotVerificationException extends BusinessException {
	public EmailNotVerificationException() {
		super(ErrorCode.EMAIL_NOT_VERIFIED);
	}
}
