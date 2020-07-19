package com.workstore.user.modules.pay.api.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class KakaoPayApprovalResponse {
	private String aid, tid, cid, sid;
	private String partner_order_id, partner_user_id, payment_method_type;
	private MoneyResponse amount;
	private CardResponse card_info;
	private String item_name, item_code, payload;
	private Integer quantity, tax_free_amount, vat_amount;
	private LocalDateTime created_at, approved_at;
}
