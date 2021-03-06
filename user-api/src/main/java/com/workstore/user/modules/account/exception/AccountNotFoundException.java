package com.workstore.user.modules.account.exception;

import com.workstore.user.modules.common.error.ErrorCode;
import com.workstore.user.modules.common.exception.BusinessException;

public class AccountNotFoundException extends BusinessException {
	public AccountNotFoundException() {
		super(ErrorCode.NOT_FOUND);
	}
}