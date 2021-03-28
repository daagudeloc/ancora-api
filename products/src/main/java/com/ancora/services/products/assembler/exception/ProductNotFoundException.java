package com.ancora.services.products.assembler.exception;

public class ProductNotFoundException extends RuntimeException {
	public ProductNotFoundException(final String type) {
		super(String.format("Could not find product %s.", type));
	}
}