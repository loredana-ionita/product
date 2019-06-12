package com.rakuten.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rakuten.product.common.builder.CategoryBuilder;
import com.rakuten.product.domain.entity.Category;
import com.rakuten.product.domain.vo.CategoryVO;
import com.rakuten.product.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	CategoryBuilder categoryBuilder;

	/**
	 * @param categoryVO
	 * @return saved catalog
	 */
	public Category saveCatalog(CategoryVO categoryVO) {
		return categoryRepository.save(categoryBuilder.buildCategoryFromCategoryVO(categoryVO));
	}
}
