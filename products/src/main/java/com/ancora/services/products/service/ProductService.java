package com.ancora.services.products.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ancora.services.products.domain.Product;
import com.ancora.services.products.repository.ProductRepository;

import lombok.Data;

@Data
@Service
public class ProductService {
	
	@Autowired
	private final ProductRepository repository;
	
	public List<Product> getAllProducts() {
		return repository.findAll();
	}
	
	public Optional<Product> findProductByType(final String type) {
		return repository.findByType(type);
	}

	public List<Product> findProductsByCategory(final String category) {
		return repository.findByCategory(category);
	}
	
	public Product saveProductData(final Product product) {
		return repository.save(product);
	}
	
	public void deleteProduct(final Product product) {
		repository.delete(product);
	}

}
