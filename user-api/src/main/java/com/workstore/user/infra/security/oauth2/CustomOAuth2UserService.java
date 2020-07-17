package com.workstore.user.infra.security.oauth2;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.workstore.common.modules.account.domain.Account;
import com.workstore.common.modules.account.domain.AccountRepository;
import com.workstore.common.modules.account.domain.AuthProvider;
import com.workstore.common.modules.account.domain.Role;
import com.workstore.user.infra.security.UserPrincipal;
import com.workstore.user.infra.security.exception.OAuth2AuthenticationProcessingException;
import com.workstore.user.infra.security.oauth2.provider.OAuth2UserInfo;
import com.workstore.user.infra.security.oauth2.provider.OAuth2UserInfoFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
	private final AccountRepository accountRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

		try {
			return processOAuth2User(oAuth2UserRequest, oAuth2User);
		} catch (AuthenticationException ex) {
			throw ex;
		} catch (Exception ex) {
			// Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
			throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
		}
	}

	private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
		OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
		if(StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
			throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
		}

		Optional<Account> userOptional = accountRepository.findByEmail(oAuth2UserInfo.getEmail());
		Account account;
		if(userOptional.isPresent()) {
			account = userOptional.get();
			if(!account.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
				throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
					account.getProvider() + " account. Please use your " + account.getProvider() +
					" account to login.");
			}
			account = updateExistingUser(account, oAuth2UserInfo);
		} else {
			account = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
		}

		return UserPrincipal.create(account, oAuth2User.getAttributes());
	}

	private Account registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
		Account account = new Account();
		int redirectUriLength = oAuth2UserRequest.getClientRegistration().getRedirectUriTemplate().length();
		String role;
		if(redirectUriLength == 55) {
			role = oAuth2UserRequest.getClientRegistration().getRedirectUriTemplate().substring(51);
		}else {
			role = oAuth2UserRequest.getClientRegistration().getRedirectUriTemplate().substring(52);
		}
		account.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
		account.setProviderId(oAuth2UserInfo.getId());
		account.setNickname(oAuth2UserInfo.getName());
		account.setEmail(oAuth2UserInfo.getEmail());
		account.setImageUrl(oAuth2UserInfo.getImageUrl());
		account.setRole(Role.valueOf(role.toUpperCase()));
		return accountRepository.save(account);
	}

	private Account updateExistingUser(Account existingAccount, OAuth2UserInfo oAuth2UserInfo) {
		existingAccount.setNickname(oAuth2UserInfo.getName());
		existingAccount.setImageUrl(oAuth2UserInfo.getImageUrl());
		return accountRepository.save(existingAccount);
	}
}

