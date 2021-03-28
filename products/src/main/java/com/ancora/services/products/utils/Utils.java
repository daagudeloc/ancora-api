package com.ancora.services.products.utils;

import com.ancora.services.products.domain.Product;

public class Utils {
	
	public static Product mapToNewProduct(final Product product, final Product newProduct) {
		
		product.setType(newProduct.getType());
		product.setPrice(newProduct.getPrice());
		product.setPrefix(newProduct.getPrefix());
		product.setCategory(newProduct.getCategory());
		product.setQuantity(newProduct.getQuantity());
		
		return product;
	}

}
