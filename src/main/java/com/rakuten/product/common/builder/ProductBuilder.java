package com.rakuten.product.common.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rakuten.product.domain.Price;
import com.rakuten.product.domain.entity.Product;
import com.rakuten.product.domain.enums.Currency;
import com.rakuten.product.domain.vo.ProductVO;
import com.rakuten.product.service.ConverterService;

@Component
public class ProductBuilder {

	@Autowired
	ConverterService converterService;

	/**
	 * @param productVO
	 * @return product build from productVO
	 */
	public Product buildProductFromProductVO(ProductVO productVO) {
		return GenericBuilder.of(Product::new).with(Product::setName, productVO.getName())
				.with(Product::setPrice, getPrice(productVO)).build();
	}

	/**
	 * @param productVO
	 * @return price value and currency, if not already converted it into EUR
	 */
	private Price getPrice(ProductVO productVO) {
		Price price = new Price(productVO.getPrice().getValue(), Currency.create(productVO.getPrice().getCurrency()));

		if (!Currency.EUR.equals(price.getCurrency())) {
			return converterService.convert(price);
		}

		return price;
	}

}
