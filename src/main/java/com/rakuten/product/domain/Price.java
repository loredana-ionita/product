package com.rakuten.product.domain;

import java.math.BigDecimal;

import com.rakuten.product.domain.enums.Currency;

import lombok.Data;

@Data
public class Price {
	
	private BigDecimal value;
	
	private Currency currency;

}
