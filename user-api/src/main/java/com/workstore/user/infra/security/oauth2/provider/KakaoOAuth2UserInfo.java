package com.workstore.user.infra.security.oauth2.provider;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {
	public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public String getId() {
		return String.valueOf(attributes.get("id"));
	}

	@Override
	public String getName() {
		if(attributes.containsKey("properties")) {
			Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
			if(properties.containsKey("nickname")) {
				return (String)properties.get("nickname");
			}
		}
		return null;
	}

	@Override
	public String getEmail() {
		if(attributes.containsKey("kakao_account")) {
			Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
			if(account.containsKey("email")) {
				return (String)account.get("email");
			}
		}
		return null;
	}

	@Override
	public String getImageUrl() {
		if(attributes.containsKey("properties")) {
			Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
			if(properties.containsKey("profile_image")) {
				return (String)properties.get("profile_image");
			}
		}
		return null;
	}


}
