package com.workstore.admin.modules.account.exception;

import com.workstore.admin.modules.common.error.ErrorCode;
import com.workstore.admin.modules.common.exception.BusinessException;

public class PasswordMismatchedException extends BusinessException {
	public PasswordMismatchedException(String msg) {
		super(msg, ErrorCode.BAD_REQUEST);
	}
}
