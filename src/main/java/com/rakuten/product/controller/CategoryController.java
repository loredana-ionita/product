package com.rakuten.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.product.domain.entity.Category;
import com.rakuten.product.domain.vo.CategoryVO;
import com.rakuten.product.service.CategoryService;

@RestController()
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@PostMapping(value = "/category")
	public Category saveCategory(@RequestBody CategoryVO categoryVO) {
		return categoryService.saveCatalog(categoryVO);
	}
	

}
