package com.rakuten.product.domain.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.rakuten.product.domain.Price;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	@Id
	private String id;
	
	private String name;
	
	private Price price;
	
	@DBRef(lazy = true)
	private List<Category> categoryList;
	
}
