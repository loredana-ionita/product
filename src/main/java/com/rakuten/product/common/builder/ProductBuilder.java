package com.rakuten.product.common.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rakuten.product.domain.Price;
import com.rakuten.product.domain.entity.Product;
import com.rakuten.product.domain.vo.ProductVO;
import com.rakuten.product.service.ConverterService;

@Component
public class ProductBuilder {

	@Autowired
	ConverterService converterService;

	public Product buildProductFromProductVO(ProductVO productVO) {
		return GenericBuilder.of(Product::new).with(Product::setName, productVO.getName())
				.with(Product::setPrice, getPrice(productVO)).build();
	}

	/**
	 * @param productVO
	 * @return price value and currency, if not already converted it into EUR
	 */
	private Price getPrice(ProductVO productVO) {
		return converterService.convert(new Price(productVO.getPrice().getValue(), productVO.getPrice().getCurrency()));
	}

}
