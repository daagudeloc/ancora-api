package com.ancora.services.products.assembler.exception.advice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ancora.services.products.assembler.exception.ProductNotFoundException;

@RestControllerAdvice
public class ProductNotFoundAdvice {
	
	@ResponseBody
	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(NOT_FOUND)
	public BaseExceptionAdvice productNotFoundHandler(final ProductNotFoundException ex) {
		return new BaseExceptionAdvice(ex.getMessage(), LocalDateTime.now(), NOT_FOUND);
	}

}
