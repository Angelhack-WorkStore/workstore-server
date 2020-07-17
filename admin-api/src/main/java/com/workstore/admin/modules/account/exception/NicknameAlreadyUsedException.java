package com.workstore.admin.modules.account.exception;

import com.workstore.admin.modules.common.error.ErrorCode;
import com.workstore.admin.modules.common.exception.BusinessException;

public class NicknameAlreadyUsedException extends BusinessException {
	public NicknameAlreadyUsedException() {
		super(ErrorCode.NICKNAME_ALREADY_USED);
	}
}
