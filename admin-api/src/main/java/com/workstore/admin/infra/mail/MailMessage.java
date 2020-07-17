package com.workstore.admin.infra.mail;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MailMessage {
	private String to;
	private String subject;
	private String message;
}
