package com.ancora.services.{{cookiecutter.service_name}}.controller;

import static org.springframework.hateoas.IanaLinkRelations.SELF;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ancora.services.{{cookiecutter.service_name}}.assembler.{{cookiecutter.service_class_name}}ModelAssembler;
import com.ancora.services.{{cookiecutter.service_name}}.assembler.exception.{{cookiecutter.service_class_name}}NotFoundException;
import com.ancora.services.{{cookiecutter.service_name}}.domain.{{cookiecutter.service_class_name}};
import com.ancora.services.{{cookiecutter.service_name}}.service.{{cookiecutter.service_class_name}}Service;
import com.ancora.services.{{cookiecutter.service_name}}.utils.Utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RestController
@RequestMapping("/{{cookiecutter.service_name}}")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class {{cookiecutter.service_class_name}}Controller {

	private final {{cookiecutter.service_class_name}}Service {{cookiecutter.service_instance_name}}Service;
	private final {{cookiecutter.service_class_name}}ModelAssembler modelAssembler;
	
	@GetMapping("/all")
	public CollectionModel<EntityModel<{{cookiecutter.service_class_name}}>> findAll{{cookiecutter.service_class_name}}s() {
		final List<EntityModel<{{cookiecutter.service_class_name}}>> {{cookiecutter.service_name}} = {{cookiecutter.service_instance_name}}Service.getAll{{cookiecutter.service_class_name}}s()
				.stream()
				.map( modelAssembler::toModel )
				.collect(Collectors.toList());
		
		return CollectionModel.of({{cookiecutter.service_name}},
				linkTo(methodOn({{cookiecutter.service_class_name}}Controller.class).findAll{{cookiecutter.service_class_name}}s()).withSelfRel());
	}

	@GetMapping("/{ {{cookiecutter.service_instance_name}}Number}")
	public EntityModel<{{cookiecutter.service_class_name}}> find{{cookiecutter.service_class_name}}ByNumber(@PathVariable final String {{cookiecutter.service_instance_name}}Number) {
		final {{cookiecutter.service_class_name}} {{cookiecutter.service_instance_name}} = {{cookiecutter.service_instance_name}}Service.find{{cookiecutter.service_class_name}}ByNumber({{cookiecutter.service_instance_name}}Number)
				.orElseThrow(() -> new {{cookiecutter.service_class_name}}NotFoundException({{cookiecutter.service_instance_name}}Number));
		
		return modelAssembler.toModel({{cookiecutter.service_instance_name}});
	}

	@PostMapping("/new")
	public ResponseEntity<?> create{{cookiecutter.service_class_name}}(@RequestBody final {{cookiecutter.service_class_name}} {{cookiecutter.service_instance_name}}) {
		
		final EntityModel<{{cookiecutter.service_class_name}}> model = modelAssembler.toModel( {{cookiecutter.service_instance_name}}Service.save{{cookiecutter.service_class_name}}Data({{cookiecutter.service_instance_name}}) );
		
		return ResponseEntity
				.created(model.getRequiredLink(SELF).toUri())
				.body(model);
	}

	@PutMapping("/update/{ {{cookiecutter.service_instance_name}}Number}")
	public ResponseEntity<?> update{{cookiecutter.service_class_name}}(@RequestBody final {{cookiecutter.service_class_name}} new{{cookiecutter.service_class_name}}, @PathVariable final String {{cookiecutter.service_instance_name}}Number) {
		final {{cookiecutter.service_class_name}} updated{{cookiecutter.service_class_name}} = {{cookiecutter.service_instance_name}}Service.find{{cookiecutter.service_class_name}}ByNumber({{cookiecutter.service_instance_name}}Number)
				.map({{cookiecutter.service_instance_name}} -> Utils.mapToNew{{cookiecutter.service_class_name}}({{cookiecutter.service_instance_name}}, new{{cookiecutter.service_class_name}}))
				.orElseThrow(() -> new {{cookiecutter.service_class_name}}NotFoundException({{cookiecutter.service_instance_name}}Number));
		
		final EntityModel<{{cookiecutter.service_class_name}}> model = modelAssembler.toModel( {{cookiecutter.service_instance_name}}Service.save{{cookiecutter.service_class_name}}Data( updated{{cookiecutter.service_class_name}} ));
		
		return ResponseEntity
				.created(model.getRequiredLink(SELF).toUri())
				.body(model);
	}
	
	@DeleteMapping("/{ {{cookiecutter.service_instance_name}}Number}")
	public ResponseEntity<?> delete{{cookiecutter.service_class_name}}(@PathVariable final String {{cookiecutter.service_instance_name}}Number) {
		final {{cookiecutter.service_class_name}} {{cookiecutter.service_instance_name}} = {{cookiecutter.service_instance_name}}Service.find{{cookiecutter.service_class_name}}ByNumber({{cookiecutter.service_instance_name}}Number)
				.orElseThrow(() -> new {{cookiecutter.service_class_name}}NotFoundException({{cookiecutter.service_instance_name}}Number));
		
		{{cookiecutter.service_instance_name}}Service.delete{{cookiecutter.service_class_name}}({{cookiecutter.service_instance_name}});
		
		return ResponseEntity.noContent().build();
	}

}
