package com.rakuten.product.common.builder;

import org.springframework.stereotype.Component;

import com.rakuten.product.domain.Price;
import com.rakuten.product.domain.entity.Product;
import com.rakuten.product.domain.vo.ProductVO;

@Component
public class ProductBuilder {
	
	public Product buildProductFromProductVO(ProductVO productVO) {
		return GenericBuilder.of(Product::new)
				.with(Product::setName, productVO.getName())
				.with(Product::setPrice, getPrice(productVO))
				.build();
	}
	
	private Price getPrice(ProductVO productVO) {
		Price price = new Price();
		price.setValue(productVO.getPrice().getValue());
		price.setCurrency(productVO.getPrice().getCurrency());

		return price;
	}

}
