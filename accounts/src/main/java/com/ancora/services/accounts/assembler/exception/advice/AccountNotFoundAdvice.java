package com.ancora.services.accounts.assembler.exception.advice;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ancora.services.accounts.assembler.exception.AccountNotFoundException;

@RestControllerAdvice
public class AccountNotFoundAdvice {
	
	@ResponseBody
	@ExceptionHandler(AccountNotFoundException.class)
	@ResponseStatus(NOT_FOUND)
	public BaseExceptionAdvice accountNotFoundHandler(final AccountNotFoundException ex) {
		return new BaseExceptionAdvice(ex.getMessage(), LocalDateTime.now(), NOT_FOUND);
	}

}
