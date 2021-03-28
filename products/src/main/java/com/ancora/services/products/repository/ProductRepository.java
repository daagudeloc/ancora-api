package com.ancora.services.products.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.ancora.services.products.domain.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, Long> {

	@Query("{ 'type': ?0 }")
	Optional<Product> findByType(final String type);

	@Query("{ 'category': ?0 }")
	List<Product> findByCategory(final String category);

}
