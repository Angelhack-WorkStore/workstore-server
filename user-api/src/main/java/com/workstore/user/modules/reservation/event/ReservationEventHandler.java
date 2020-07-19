package com.workstore.user.modules.reservation.event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.workstore.common.modules.account.domain.Account;
import com.workstore.common.modules.reservation.domain.event.ReservationCompletedEvent;
import com.workstore.user.infra.config.UserAppProperties;
import com.workstore.user.infra.mail.MailMessage;
import com.workstore.user.infra.mail.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Async
@Component
@Transactional
@RequiredArgsConstructor
public class ReservationEventHandler {
	private final MailService mailService;
	private final UserAppProperties appProperties;
	private final TemplateEngine templateEngine;

	@EventListener
	public void handle(ReservationCompletedEvent event) {
		// TODO 공간 등록 완료와 함께 인기 있는 공간 정보도 추천해보자.
		Account reservationUser = event.getAccount();
		Context context = new Context();
		context.setVariable("nickname", reservationUser.getNickname());
		context.setVariable("message", "WorkStore에서 선택하신 공간이 예약 완료되었습니다!");
		context.setVariable("host", appProperties.getMail().getHost());
		String message = templateEngine.process("mail/pay-success", context);

		MailMessage emailMessage = MailMessage.builder()
			.to(reservationUser.getEmail())
			.subject("WorkStore 공간등록 완료 메일")
			.message(message)
			.build();

		mailService.send(emailMessage);
	}
}
