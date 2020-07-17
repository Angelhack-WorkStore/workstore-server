package com.workstore.user.modules.account.api.response;

import lombok.Data;

@Data
public class TokenResponse {
	private String accessToken;
	private String tokenType = "Bearer";

	public TokenResponse(String accessToken) {
		this.accessToken = accessToken;
	}
}
