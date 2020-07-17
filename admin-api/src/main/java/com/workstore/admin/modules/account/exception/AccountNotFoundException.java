package com.workstore.admin.modules.account.exception;

import com.workstore.admin.modules.common.error.ErrorCode;
import com.workstore.admin.modules.common.exception.BusinessException;

public class AccountNotFoundException extends BusinessException {
	public AccountNotFoundException() {
		super(ErrorCode.NOT_FOUND);
	}
}