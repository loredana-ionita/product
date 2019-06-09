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

	public Product saveProduct(ProductVO productVO) {
		return productRepository.save(productBuilder.buildProductFromProductVO(productVO));
	}

	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	public BasicResponseVO assignCategories(String productId, List<String> categoryIds) {
		BasicResponseVO basicResponseVO = new BasicResponseVO();

		Optional<Product> productOptional = productRepository.findById(productId);
		if (productOptional.isPresent()) {
			Product product = productOptional.get();
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
		}else {
			throw new ProductNotFoundException(productId);
		}

		basicResponseVO.setStatus(HttpStatus.OK);
		return basicResponseVO;
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

	public BasicResponseVO removeCategories(String productId, List<String> categoryIds) {
		BasicResponseVO basicResponseVO = new BasicResponseVO();

		Boolean updated = false;

		Optional<Product> productOptional = productRepository.findById(productId);
		if (productOptional.isPresent()) {
			Product product = productOptional.get();

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
		} else {
			throw new ProductNotFoundException(productId);
		}
		basicResponseVO.setStatus(HttpStatus.OK);
		return basicResponseVO;
	}

}
