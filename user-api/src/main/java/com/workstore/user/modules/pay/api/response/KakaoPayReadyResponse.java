package com.workstore.user.modules.pay.api.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoPayReadyResponse {
	private String tid;
	private String next_redirect_pc_url;
	private LocalDateTime created_at;
}
