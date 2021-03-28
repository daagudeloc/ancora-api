package com.ancora.services.accounts.utils;

import com.ancora.services.accounts.domain.Account;

public class Utils {
	
	public static Account mapToNewAccount(final Account account, final Account newAccount) {
		
		account.setFirstName(newAccount.getFirstName());
		account.setLastName(newAccount.getLastName());
		account.setBirthDate(newAccount.getBirthDate());
		account.setBalance(newAccount.getBalance());
		account.setIdNumber(newAccount.getIdNumber());
		account.setNumber(newAccount.getNumber());
		
		return account;
	}

}
