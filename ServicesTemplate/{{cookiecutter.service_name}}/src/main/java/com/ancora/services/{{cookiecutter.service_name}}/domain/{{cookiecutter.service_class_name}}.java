package com.ancora.services.{{cookiecutter.service_name}}.domain;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "{{cookiecutter.service_name}}")
public class {{cookiecutter.service_class_name}} {
	
	@Id
	private String id;
	protected String number;
	protected String idNumber;
	protected LocalDate birthDate;
	protected String firstName;
	protected String lastName;
	protected Short balance;
	
	public {{cookiecutter.service_class_name}}(final String number, final String idNumber, final LocalDate birthDate, final String firstName, final String lastName, final Short balance) {
		this.number = number;
		this.idNumber = idNumber;
		this.birthDate = birthDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = balance;
	}
	
}
