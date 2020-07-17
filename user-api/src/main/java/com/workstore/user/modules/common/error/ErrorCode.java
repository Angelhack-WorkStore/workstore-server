package com.workstore.user.modules.common.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
	BAD_REQUEST(400, "C000", "Bad request"),
	INVALID_INPUT(400, "C001", "Invalid Input"),
	UN_AUTHORIZED(401, "C002", "UnAuthorized"),
	FORBIDDEN(403, "C003", "Forbidden Request"),
	NOT_FOUND(404, "C004", "Resource Not Found"),
	METHOD_NOT_ALLOWED(405, "C005", "Method Now Allowed"),
	INTERNAL_SERVER_ERROR(500, "C006", "Server Error"),

	EMAIL_TOKEN_INVALID(400, "E001", "Email Token Invalid"),
	EMAIL_ALREADY_USE(400, "E002", "This Email already used"),
	EMAIL_NOT_VERIFIED(400, "E003", "This Email Not Verified"),

	NICKNAME_ALREADY_USED(400, "N001", "This Nickname already used");

	private String code;
	private String message;
	private int status;

	ErrorCode(int status, String code, String message) {
		this.status = status;
		this.message = message;
		this.code = code;
	}
}
