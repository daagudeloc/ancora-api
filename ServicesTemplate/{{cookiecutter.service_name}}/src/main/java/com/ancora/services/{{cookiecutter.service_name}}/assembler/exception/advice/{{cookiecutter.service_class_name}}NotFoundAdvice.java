package com.ancora.services.{{cookiecutter.service_name}}.assembler.exception.advice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ancora.services.{{cookiecutter.service_name}}.assembler.exception.{{cookiecutter.service_class_name}}NotFoundException;

@RestControllerAdvice
public class {{cookiecutter.service_class_name}}NotFoundAdvice {
	
	@ResponseBody
	@ExceptionHandler({{cookiecutter.service_class_name}}NotFoundException.class)
	@ResponseStatus(NOT_FOUND)
	public BaseExceptionAdvice {{cookiecutter.service_instance_name}}NotFoundHandler(final {{cookiecutter.service_class_name}}NotFoundException ex) {
		return new BaseExceptionAdvice(ex.getMessage(), LocalDateTime.now(), NOT_FOUND);
	}

}
