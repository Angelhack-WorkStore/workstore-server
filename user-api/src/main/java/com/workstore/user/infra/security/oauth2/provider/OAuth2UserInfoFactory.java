package com.workstore.user.infra.security.oauth2.provider;

import java.util.Map;

import com.workstore.common.modules.account.domain.AuthProvider;

public class OAuth2UserInfoFactory {

	public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
		if(registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
			return new GoogleOAuth2UserInfo(attributes);
		} else if (registrationId.equalsIgnoreCase(AuthProvider.kakao.toString())) {
			return new KakaoOAuth2UserInfo(attributes);
		}  else {
			throw new IllegalStateException("Sorry! Login with " + registrationId + " is not supported yet.");
		}
	}
}
