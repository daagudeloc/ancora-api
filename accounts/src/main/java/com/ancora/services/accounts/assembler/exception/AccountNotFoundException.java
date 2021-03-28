package com.ancora.services.accounts.assembler.exception;

public class AccountNotFoundException extends RuntimeException {
	public AccountNotFoundException(final String number) {
		super(String.format("Could not find account No. %s", number));
	}
}