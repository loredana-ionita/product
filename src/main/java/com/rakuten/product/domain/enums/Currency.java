package com.rakuten.product.domain.enums;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Currency {
	BTC("btc"), CAD("cad"), EUR("eur"), GBP("gbp"), RON("ron"), USD("usd");

	private String value;
	
	@JsonValue
	public String getValue() {
		return this.value;
	}

	@JsonCreator
	public static Currency create(String value) {
		if (value == null) {
            return Currency.EUR;
        }
		return Arrays.stream(Currency.values()).filter(currency -> currency.getValue().equalsIgnoreCase(value)).findFirst()
				.orElse(EUR);
	}

}
