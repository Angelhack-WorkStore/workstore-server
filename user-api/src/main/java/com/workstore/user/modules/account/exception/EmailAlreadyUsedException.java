package com.workstore.user.modules.account.exception;

import com.workstore.user.modules.common.error.ErrorCode;
import com.workstore.user.modules.common.exception.BusinessException;

public class EmailAlreadyUsedException extends BusinessException {
	public EmailAlreadyUsedException() {
		super(ErrorCode.EMAIL_ALREADY_USE);
	}
}
