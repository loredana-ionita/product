package com.rakuten.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rakuten.product.common.builder.ProductBuilder;
import com.rakuten.product.domain.entity.Product;
import com.rakuten.product.domain.vo.ProductVO;
import com.rakuten.product.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductBuilder productBuilder;

	public Product saveProduct(ProductVO productVO) {
		return productRepository.save(productBuilder.buildProductFromProductVO(productVO));
	}

	public List<Product> getProducts() {
		return productRepository.findAll();
	}

}
