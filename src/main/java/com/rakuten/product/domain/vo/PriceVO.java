package com.rakuten.product.domain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PriceVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6761701759205911209L;

	@NotNull
	@DecimalMin(value = "0.0", inclusive = false)
	private BigDecimal value;

	private String currency;
	
}
