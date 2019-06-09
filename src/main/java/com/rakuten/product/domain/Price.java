package com.rakuten.product.domain;

import java.math.BigDecimal;

import com.rakuten.product.domain.enums.Currency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {
	
	private BigDecimal value;
	
	private Currency currency;

}
