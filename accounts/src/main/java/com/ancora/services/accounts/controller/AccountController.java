package com.ancora.services.accounts.controller;

import static org.springframework.hateoas.IanaLinkRelations.SELF;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ancora.services.accounts.assembler.AccountModelAssembler;
import com.ancora.services.accounts.assembler.exception.AccountNotFoundException;
import com.ancora.services.accounts.domain.Account;
import com.ancora.services.accounts.service.AccountService;
import com.ancora.services.accounts.utils.Utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountController {

	private final AccountService accountService;
	private final AccountModelAssembler modelAssembler;
	
	@GetMapping("/all")
	@CrossOrigin(origins = "http://localhost:5000")
	public CollectionModel<EntityModel<Account>> findAllAccounts() {
		final List<EntityModel<Account>> accounts = accountService.getAllAccounts()
				.stream()
				.map( modelAssembler::toModel )
				.collect(Collectors.toList());
		
		return CollectionModel.of(accounts,
				linkTo(methodOn(AccountController.class).findAllAccounts()).withSelfRel());
	}

	@GetMapping("/cell/{cellNumber}")
	@CrossOrigin(origins = "http://localhost:5000")
	public EntityModel<Account> findAccountByCellNumber(@PathVariable final String cellNumber) {
		final Account account = accountService.findAccountByCellNumber(cellNumber)
				.orElseThrow(() -> new AccountNotFoundException(cellNumber));
		
		return modelAssembler.toModel(account);
	}

	@GetMapping("/id/{idNumber}")
	@CrossOrigin(origins = "http://localhost:5000")
	public EntityModel<Account> findAccountByIdNumber(@PathVariable final String idNumber) {
		final Account account = accountService.findAccountByIdNumber(idNumber)
				.orElseThrow(() -> new AccountNotFoundException(idNumber));
		
		return modelAssembler.toModel(account);
	}

	@PostMapping("/new")
	public ResponseEntity<?> createAccount(@RequestBody final Account account) {
		
		final EntityModel<Account> model = modelAssembler.toModel( accountService.saveAccountData(account) );
		
		return ResponseEntity
				.created(model.getRequiredLink(SELF).toUri())
				.body(model);
	}

	@PutMapping("/cell/{cellNumber}")
	public ResponseEntity<?> updateAccountWithCellNumber(@RequestBody final Account newAccount, @PathVariable final String cellNumber) {
		final Account updatedAccount = accountService.findAccountByCellNumber(cellNumber)
				.map(account -> Utils.mapToNewAccount(account, newAccount))
				.orElseThrow(() -> new AccountNotFoundException(cellNumber));
		
		final EntityModel<Account> model = modelAssembler.toModel( accountService.saveAccountData( updatedAccount ));
		
		return ResponseEntity
				.created(model.getRequiredLink(SELF).toUri())
				.body(model);
	}

	@PutMapping("/id/{idNumber}")
	public ResponseEntity<?> updateAccountWithIdNumber(@RequestBody final Account newAccount, @PathVariable final String idNumber) {
		final Account updatedAccount = accountService.findAccountByIdNumber(idNumber)
				.map(account -> Utils.mapToNewAccount(account, newAccount))
				.orElseThrow(() -> new AccountNotFoundException(idNumber));
		
		final EntityModel<Account> model = modelAssembler.toModel( accountService.saveAccountData( updatedAccount ));
		
		return ResponseEntity
				.created(model.getRequiredLink(SELF).toUri())
				.body(model);
	}
	
	@DeleteMapping("/cell/{cellNumber}")
	public ResponseEntity<?> deleteAccountWithCellNumber(@PathVariable final String cellNumber) {
		final Account account = accountService.findAccountByCellNumber(cellNumber)
				.orElseThrow(() -> new AccountNotFoundException(cellNumber));
		
		accountService.deleteAccount(account);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/id/{idNumber}")
	public ResponseEntity<?> deleteAccountWithIdNumber(@PathVariable final String idNumber) {
		final Account account = accountService.findAccountByIdNumber(idNumber)
				.orElseThrow(() -> new AccountNotFoundException(idNumber));
		
		accountService.deleteAccount(account);
		
		return ResponseEntity.noContent().build();
	}

}
