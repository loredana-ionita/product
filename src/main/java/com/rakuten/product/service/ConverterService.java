package com.rakuten.product.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rakuten.product.common.Constants;
import com.rakuten.product.controller.exception.ThirdPartyException;
import com.rakuten.product.domain.ConverterResponse;
import com.rakuten.product.domain.Price;
import com.rakuten.product.domain.enums.Currency;

@Service
public class ConverterService {

	@Autowired
	RestTemplate restTemplate;

	@Value("${fixer.url}")
	private String fixerUrl;

	@Value("${fixer.access_key}")
	private String accessKey;

	/**
	 * @param price
	 * @return price converted to EUR currency based on value obained from fixer.io latest
	 */
	public Price convert(Price price) {

		String url = new StringBuilder(fixerUrl).append(Constants.LATEST_FIXER_ENDPOINT).append(Constants.QUESTION_MARK)
				.append(Constants.ACCESS_KEY).append(Constants.EQUALS_MARK).append(accessKey)
				.append(Constants.AMPERSAND_MARK).append(Constants.LATEST_FIXER_SYMBOLS).append(Constants.EQUALS_MARK)
				.append(price.getCurrency()).toString();

		ConverterResponse converterResponse = restTemplate.getForObject(url, ConverterResponse.class);

		if (converterResponse.getSuccess()) {
			BigDecimal priceValue = converterResponse.getRates().get(price.getCurrency());
			if (priceValue != null) {
				return new Price(price.getValue().divide(priceValue, 4, RoundingMode.CEILING), Currency.EUR);
			}
		}

		throw new ThirdPartyException();
	}

}
