package com.ancora.services.products.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "products")
public class Product {
	
	@Id
	private String id;
	protected String type;
	protected Integer price;
	protected String prefix;
	protected String category;
	protected Integer quantity;
	
	public Product(final String type, final Integer price, final String prefix, final String category, final Integer quantity) {
		this.type = type;
		this.price = price;
		this.prefix = prefix;
		this.category = category;
		this.quantity = quantity;
	}
	
}
