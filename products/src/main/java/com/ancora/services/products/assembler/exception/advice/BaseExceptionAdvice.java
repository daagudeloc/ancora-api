package com.ancora.services.products.assembler.exception.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class BaseExceptionAdvice {
	
	private final String message;
	private final LocalDateTime timestamp;
	private final HttpStatus status;
	
}
