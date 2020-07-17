package com.workstore.admin.modules.account.exception;

import com.workstore.admin.modules.common.error.ErrorCode;
import com.workstore.admin.modules.common.exception.BusinessException;

public class EmailAlreadyUsedException extends BusinessException {
	public EmailAlreadyUsedException() {
		super(ErrorCode.EMAIL_ALREADY_USE);
	}
}
