package com.workstore.user.modules.reservation.exception;

import com.workstore.user.modules.common.error.ErrorCode;
import com.workstore.user.modules.common.exception.BusinessException;

public class ReservationNotFoundException extends BusinessException {
	public ReservationNotFoundException() {
		super(ErrorCode.NOT_FOUND);
	}
}
