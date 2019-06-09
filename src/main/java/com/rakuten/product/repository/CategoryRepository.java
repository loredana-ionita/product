package com.rakuten.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rakuten.product.domain.entity.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

}
