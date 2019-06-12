package com.rakuten.product.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import com.rakuten.product.domain.enums.Currency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3290699436815042843L;

	private BigDecimal value;

	private Currency currency;
	
}
