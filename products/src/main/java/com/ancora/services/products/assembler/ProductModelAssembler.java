package com.ancora.services.products.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.ancora.services.products.controller.ProductController;
import com.ancora.services.products.domain.Product;

@Component
public class ProductModelAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>>{

	@Override
	public EntityModel<Product> toModel(final Product product) {
		return EntityModel.of(product,
				linkTo(methodOn(ProductController.class).findProductByType(product.getType())).withSelfRel(),
				linkTo(methodOn(ProductController.class).findAllProducts()).withRel("products"));
	}

}
