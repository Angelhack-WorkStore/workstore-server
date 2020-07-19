package com.workstore.user.modules.account.exception;

import com.workstore.user.modules.common.error.ErrorCode;
import com.workstore.user.modules.common.exception.BusinessException;

public class PasswordMismatchedException extends BusinessException {
	public PasswordMismatchedException(String msg) {
		super(msg, ErrorCode.BAD_REQUEST);
	}
}
