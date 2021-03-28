package com.ancora.services.{{cookiecutter.service_name}}.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.ancora.services.{{cookiecutter.service_name}}.controller.{{cookiecutter.service_class_name}}Controller;
import com.ancora.services.{{cookiecutter.service_name}}.domain.{{cookiecutter.service_class_name}};

@Component
public class {{cookiecutter.service_class_name}}ModelAssembler implements RepresentationModelAssembler<{{cookiecutter.service_class_name}}, EntityModel<{{cookiecutter.service_class_name}}>>{

	@Override
	public EntityModel<{{cookiecutter.service_class_name}}> toModel(final {{cookiecutter.service_class_name}} {{cookiecutter.service_instance_name}}) {
		return EntityModel.of({{cookiecutter.service_instance_name}},
				linkTo(methodOn({{cookiecutter.service_class_name}}Controller.class).find{{cookiecutter.service_class_name}}ByNumber({{cookiecutter.service_instance_name}}.getNumber())).withSelfRel(),
				linkTo(methodOn({{cookiecutter.service_class_name}}Controller.class).findAll{{cookiecutter.service_class_name}}s()).withRel("{{cookiecutter.service_name}}"));
	}

}
