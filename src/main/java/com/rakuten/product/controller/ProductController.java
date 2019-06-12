package com.rakuten.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.product.domain.BasicResponseVO;
import com.rakuten.product.domain.entity.Product;
import com.rakuten.product.domain.vo.ProductVO;
import com.rakuten.product.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;

	/**
	 * @param productVO
	 * @return saved product
	 */
	@PostMapping(value = "/product")
	public ResponseEntity<Product> saveProduct(@Valid @RequestBody ProductVO productVO) {
		return new ResponseEntity<>(productService.saveProduct(productVO), HttpStatus.OK);
	}

	/**
	 * @return product list from database
	 */
	@GetMapping(value = "/product/all")
	public List<Product> getProducts() {
		return productService.getProducts();
	}
	
	/**
	 * @param productId
	 * 
	 * delete product by productId
	 * 
	 * @return
	 */
	@DeleteMapping(value = "/product/{productId}")
	public BasicResponseVO removeProduct(@PathVariable String productId) {
		BasicResponseVO basicResponseVO = new BasicResponseVO();

		productService.removeProduct(productId);

		basicResponseVO.setStatus(HttpStatus.OK);

		return basicResponseVO;
	}
	
	/**
	 * @param productId
	 * @param categories
	 * 
	 * assign list of categories to a product with productId
	 * 
	 * @return 
	 */
	@PostMapping(value = "/product/{productId}/assign")
	public BasicResponseVO assignCategories(@PathVariable String productId,
			@RequestParam(value = "categories") List<String> categories) {
		return productService.assignCategories(productId, categories);
	}

	/**
	 * @param productId
	 * @param categories
	 * 
	 * remove list of categories for a product with productId
	 * 
	 * @return
	 */
	@DeleteMapping(value = "/product/{productId}/remove")
	public BasicResponseVO removeCategories(@PathVariable("productId") String productId,
			@RequestParam(value = "categories") List<String> categories) {
		return productService.removeCategories(productId, categories);
	}
	
}
