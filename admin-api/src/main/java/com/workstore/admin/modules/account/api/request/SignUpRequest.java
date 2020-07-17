package com.workstore.admin.modules.account.api.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SignUpRequest {
	@NotBlank
	private String name;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	//@Length(min = 8, max = 50)
	private String password;
}
