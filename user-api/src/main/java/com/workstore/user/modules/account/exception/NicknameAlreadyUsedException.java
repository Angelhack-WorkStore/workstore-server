package com.workstore.user.modules.account.exception;

import com.workstore.user.modules.common.error.ErrorCode;
import com.workstore.user.modules.common.exception.BusinessException;

public class NicknameAlreadyUsedException extends BusinessException {
	public NicknameAlreadyUsedException() {
		super(ErrorCode.NICKNAME_ALREADY_USED);
	}
}
