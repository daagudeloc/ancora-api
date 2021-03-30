package com.ancora.services.accounts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ancora.services.accounts.domain.Account;
import com.ancora.services.accounts.repository.AccountRepository;

import lombok.Data;

@Data
@Service
public class AccountService {
	
	@Autowired
	private final AccountRepository repository;
	
	public List<Account> getAllAccounts() {
		return repository.findAll();
	}
	
	public Optional<Account> findAccountByCellNumber(final String cellNumber) {
		return repository.findByCellNumber(cellNumber);
	}

	public Optional<Account> findAccountByIdNumber(final String idNumber) {
		return repository.findByIdNumber(idNumber);
	}

	public List<Account> findAccountByLastName(final String lastName) {
		return repository.findByLastName(lastName);
	}
	
	public Account saveAccountData(final Account account) {
		return repository.save(account);
	}
	
	public void deleteAccount(final Account account) {
		repository.delete(account);
	}

}
