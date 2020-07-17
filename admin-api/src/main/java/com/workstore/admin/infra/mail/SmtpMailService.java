package com.workstore.admin.infra.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class SmtpMailService implements MailService {
	private final JavaMailSender javaMailSender;

	@Override
	public void send(MailMessage mailMessage) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
			helper.setTo(mailMessage.getTo());
			helper.setSubject(mailMessage.getSubject());
			helper.setText(mailMessage.getMessage(), true);

			javaMailSender.send(mimeMessage);

			log.info("sent email: {}", mailMessage.getMessage());
		} catch (MessagingException e) {
			log.error("failed to send email", e);
			throw new RuntimeException(e);
		}
	}
}