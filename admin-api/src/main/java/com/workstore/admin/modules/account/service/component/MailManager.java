package com.workstore.admin.modules.account.service.component;

import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.workstore.admin.infra.config.AdminAppProperties;
import com.workstore.admin.infra.mail.MailMessage;
import com.workstore.admin.infra.mail.MailService;
import com.workstore.common.modules.account.domain.Account;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MailManager {
	private final MailService mailService;
	private final AdminAppProperties appProperties;
	private final TemplateEngine templateEngine;

	public void sendSignUpConfirmEmail(Account newAccount) {
		Context context = new Context();
		context.setVariable("link", "/api/auth/check-email-token?token=" + newAccount.getEmailCheckToken() +
			"&email=" + newAccount.getEmail());
		context.setVariable("nickname", newAccount.getNickname());
		context.setVariable("linkName", "이메일 인증하기");
		context.setVariable("message", "WorkStore 서비스를 사용하려면 링크를 클릭하세요.");
		context.setVariable("host", appProperties.getMail().getHost());
		String message = templateEngine.process("mail/simple-link", context);

		MailMessage emailMessage = MailMessage.builder()
			.to(newAccount.getEmail())
			.subject("WorkStore, 회원 가입 인증")
			.message(message)
			.build();

		mailService.send(emailMessage);
	}
}
