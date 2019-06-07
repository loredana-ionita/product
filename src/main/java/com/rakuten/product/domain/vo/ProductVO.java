package com.rakuten.product.domain.vo;

import com.rakuten.product.domain.Price;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVO {

	private String name;
	
	private Price price;
	
}
