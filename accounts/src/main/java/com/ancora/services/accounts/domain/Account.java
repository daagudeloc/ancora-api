package com.ancora.services.accounts.domain;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "accounts")
@JsonInclude(NON_NULL)
public class Account {
	
	@Id
	private String id;
	protected String cellNumber;
	protected String idNumber;
	protected LocalDate birthDate;
	protected String firstName;
	protected String lastName;
	protected Short timeBalance;
	
	public Account(final String cellNumber, final String idNumber, final LocalDate birthDate, 
				   final String firstName, final String lastName, final Short timeBalance) {
		this.cellNumber = cellNumber;
		this.idNumber = idNumber;
		this.birthDate = birthDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.timeBalance = timeBalance;
	}
	
}
