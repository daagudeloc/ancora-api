package com.ancora.services.accounts.domain;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "accounts")
public class Account {
	
	@Id
	private String id;
	protected String number;
	protected String idNumber;
	protected LocalDate birthDate;
	protected String firstName;
	protected String lastName;
	protected Short balance;
	
	public Account(final String number, final String idNumber, final LocalDate birthDate, final String firstName, final String lastName, final Short balance) {
		this.number = number;
		this.idNumber = idNumber;
		this.birthDate = birthDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = balance;
	}
	
}
