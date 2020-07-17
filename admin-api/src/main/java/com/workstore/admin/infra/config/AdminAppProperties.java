package com.workstore.admin.infra.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;

@Getter
@Component
@ConfigurationProperties("admin")
public class AdminAppProperties {
	private final Auth auth = new Auth();
	private final OAuth2 oauth2 = new OAuth2();
	private final Mail mail = new Mail();
	private final KakaoPay kakaoPay = new KakaoPay();

	@Data
	public static class KakaoPay {
		private String adminKey;
	}

	@Data
	public static class Mail {
		private String host;
	}

	@Data
	public static class Auth {
		private String tokenSecret;
		private long tokenExpirationMsec;
	}

	public static final class OAuth2 {
		private List<String> authorizedRedirectUris = new ArrayList<>();

		public List<String> getAuthorizedRedirectUris() {
			return authorizedRedirectUris;
		}

		public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
			this.authorizedRedirectUris = authorizedRedirectUris;
			return this;
		}
	}
}
