package com.rakuten.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.product.domain.entity.Product;
import com.rakuten.product.domain.vo.ProductVO;
import com.rakuten.product.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping(name = "/product")
	public ResponseEntity<Product> saveProduct(@RequestBody ProductVO productVO) {
		return new ResponseEntity<>(productService.saveProduct(productVO), HttpStatus.OK);
	}

	@GetMapping(name = "/product/all")
	public List<Product> getProducts() {
		return productService.getProducts();
	}

}
