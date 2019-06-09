package com.rakuten.product.common.builder;

import org.springframework.stereotype.Component;

import com.rakuten.product.domain.entity.Category;
import com.rakuten.product.domain.vo.CategoryVO;

@Component
public class CategoryBuilder {

	public Category buildCategoryFromCategoryVO(CategoryVO categoryVO) {
		return GenericBuilder.of(Category::new)
				.with(Category::setName, categoryVO.getName())
				.build();
	}
}
