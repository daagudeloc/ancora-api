package com.ancora.services.{{cookiecutter.service_name}}.assembler.exception;

public class {{cookiecutter.service_class_name}}NotFoundException extends RuntimeException {
	public {{cookiecutter.service_class_name}}NotFoundException(final String number) {
		super(String.format("Could not find {{cookiecutter.service_instance_name}} No. %s", number));
	}
}