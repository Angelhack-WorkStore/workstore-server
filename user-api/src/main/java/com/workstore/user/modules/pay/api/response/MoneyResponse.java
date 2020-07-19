package com.workstore.user.modules.pay.api.response;

import lombok.Data;

@Data
public class MoneyResponse {
	private Integer total, tax_free, vat, point, discount;
}
