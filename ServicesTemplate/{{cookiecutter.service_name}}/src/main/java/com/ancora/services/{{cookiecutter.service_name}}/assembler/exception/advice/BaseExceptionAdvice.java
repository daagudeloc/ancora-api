package com.ancora.services.{{cookiecutter.service_name}}.assembler.exception.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class BaseExceptionAdvice {
	
	private final String message;
	private final LocalDateTime timestamp;
	private final HttpStatus status;
	
}
