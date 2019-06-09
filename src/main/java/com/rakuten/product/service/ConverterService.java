package com.rakuten.product.service;

import java.util.Map;

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

	public Price convert(Price price) {

		String url = new StringBuilder(fixerUrl).append(Constants.CONVERTER_ENDPOINT)
				.append(Constants.QUESTION_MARK).append(Constants.ACCESS_KEY).append(Constants.EQUALS_MARK).append(accessKey)
				.append(Constants.AMPERSAND_MARK).append(Constants.CONVERTER_FROM).append(Constants.EQUALS_MARK).append(price.getCurrency())
				.append(Constants.AMPERSAND_MARK).append(Constants.CONVERTER_TO).append(Constants.EQUALS_MARK).append(Currency.EUR)
				.append(Constants.AMPERSAND_MARK).append(Constants.CONVERTER_AMOUNT).append(Constants.EQUALS_MARK).append(price.getValue()).toString();

		ConverterResponse converterResponse = restTemplate.getForObject(url, ConverterResponse.class);

		if (converterResponse.getSuccess()) {
			return new Price(converterResponse.getResult(), Currency.EUR);
		} else {
			throw new ThirdPartyException();
		}
	}

	public String createRequestURL(Map<String, String> parameters) {
		return new StringBuilder(fixerUrl).append(parameters.get("ENDPOINT")).append(accessKey)
				.append(parameters.get("FROM")).append(parameters.get("TO")).append(parameters.get("AMOUNT"))
				.toString();
	}

}
