package com.ancora.services.products.controller;

import static org.springframework.hateoas.IanaLinkRelations.SELF;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ancora.services.products.assembler.ProductModelAssembler;
import com.ancora.services.products.assembler.exception.ProductNotFoundException;
import com.ancora.services.products.domain.Product;
import com.ancora.services.products.service.ProductService;
import com.ancora.services.products.utils.Utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {

	private final ProductService productService;
	private final ProductModelAssembler modelAssembler;
	
	@GetMapping("/all")
	@CrossOrigin(origins = "http://localhost:5000")
	public CollectionModel<EntityModel<Product>> findAllProducts() {
		final List<EntityModel<Product>> products = productService.getAllProducts()
				.stream()
				.map( modelAssembler::toModel )
				.collect(Collectors.toList());
		
		return CollectionModel.of(products,
				linkTo(methodOn(ProductController.class).findAllProducts()).withSelfRel());
	}

	@GetMapping()
	@CrossOrigin(origins = "http://localhost:5000")
	public EntityModel<Product> findProductByType(@RequestParam final String type) {
		final Product product = productService.findProductByType(type)
				.orElseThrow(() -> new ProductNotFoundException(type));
		
		return modelAssembler.toModel(product);
	}

	@GetMapping("/{category}")
	@CrossOrigin(origins = "http://localhost:5000")
	public CollectionModel<EntityModel<Product>> findProductsByCategory(@PathVariable final String category) {
		final List<EntityModel<Product>> products = productService.findProductsByCategory(category)
				.stream()
				.map( modelAssembler::toModel )
				.collect(Collectors.toList());
		
		return CollectionModel.of(products,
				linkTo(methodOn(ProductController.class).findAllProducts()).withSelfRel());
	}

	@PostMapping("/new")
	public ResponseEntity<?> createProduct(@RequestBody final Product product) {
		
		final EntityModel<Product> model = modelAssembler.toModel( productService.saveProductData(product) );
		
		return ResponseEntity
				.created(model.getRequiredLink(SELF).toUri())
				.body(model);
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateProduct(@RequestBody final Product newProduct, @RequestParam final String productType) {
		final Product updatedProduct = productService.findProductByType(productType)
				.map(product -> Utils.mapToNewProduct(product, newProduct))
				.orElseThrow(() -> new ProductNotFoundException(productType));
		
		final EntityModel<Product> model = modelAssembler.toModel( productService.saveProductData( updatedProduct ));
		
		return ResponseEntity
				.created(model.getRequiredLink(SELF).toUri())
				.body(model);
	}
	
	@DeleteMapping()
	public ResponseEntity<?> deleteProduct(@RequestParam final String type) {
		final Product product = productService.findProductByType(type)
				.orElseThrow(() -> new ProductNotFoundException(type));
		
		productService.deleteProduct(product);
		
		return ResponseEntity.noContent().build();
	}

}
