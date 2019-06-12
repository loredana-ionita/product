package com.rakuten.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rakuten.product.common.builder.ProductBuilder;
import com.rakuten.product.controller.exception.ProductNotFoundException;
import com.rakuten.product.domain.BasicResponseVO;
import com.rakuten.product.domain.entity.Category;
import com.rakuten.product.domain.entity.Product;
import com.rakuten.product.domain.vo.ProductVO;
import com.rakuten.product.repository.CategoryRepository;
import com.rakuten.product.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ProductBuilder productBuilder;

	/**
	 * @param productVO
	 * @return saved product
	 */
	public Product saveProduct(ProductVO productVO) {
		return productRepository.save(productBuilder.buildProductFromProductVO(productVO));
	}

	
	/**
	 * @return product list from database
	 */
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	
	/**
	 * @param productId
	 * @param categoryIds
	 * 
	 * assign list of categories to a product with productId
	 * 
	 * @return 
	 */
	public BasicResponseVO assignCategories(String productId, List<String> categoryIds) {
		return productRepository.findById(productId).map(product -> {
			BasicResponseVO basicResponseVO = new BasicResponseVO();

			List<Category> categories = new ArrayList<>();

			if (product.getCategoryList() != null) {
				categories = product.getCategoryList();
			}
			for (String categoryId : categoryIds) {
				Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
				if (categoryOptional.isPresent() && !isIncluded(categoryOptional.get(), categories)) {
					categories.add(categoryOptional.get());
				}
			}
			if (!categories.isEmpty()) {
				product.setCategoryList(categories);
				productRepository.save(product);
			}

			basicResponseVO.setStatus(HttpStatus.OK);

			return basicResponseVO;
		}).orElseThrow(ProductNotFoundException::new);
	}
	

	private Boolean isIncluded(Category category, List<Category> categories) {
		for (Category myCategory : categories) {
			if (category.getId().equals(myCategory.getId())) {
				return true;
			}
		}
		return false;
	}

	
	private Category getCategory(String categoryId, List<Category> categories) {
		for (Category category : categories) {
			if (categoryId.equals(category.getId())) {
				return category;
			}
		}
		return null;
	}

	
	/** 
	 * @param productId
	 * @param categoryIds
	 * 
	 * remove list of categories for a product with productId
	 * 
	 * @return
	 */
	public BasicResponseVO removeCategories(String productId, List<String> categoryIds) {
		return productRepository.findById(productId).map(product -> {
			BasicResponseVO basicResponseVO = new BasicResponseVO();
			Boolean updated = false;

			List<Category> categoryList = product.getCategoryList();
			if (!categoryList.isEmpty()) {
				for (String categoryId : categoryIds) {
					Category category = getCategory(categoryId, categoryList);
					if (category != null) {
						updated = true;
						categoryList.remove(category);
					}
				}
				if (updated) {
					product.setCategoryList(categoryList);
					productRepository.save(product);
				}
			} else {
				basicResponseVO.getMessages().add("The product doesn't have any category.");
			}

			basicResponseVO.setStatus(HttpStatus.OK);
			return basicResponseVO;
		}).orElseThrow(ProductNotFoundException::new);
	}
	
	/**
	 * @param productId
	 * 
	 * remove product by productId
	 * 
	 */
	public void removeProduct(String productId) {
		productRepository.deleteById(productId);
	}

}
