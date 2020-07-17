package com.workstore.user.modules.account.api.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginRequest {
	@NotBlank
	@Email
	private String email;
	@NotBlank
	//@Length(min = 8, max = 50)
	private String password;
}
