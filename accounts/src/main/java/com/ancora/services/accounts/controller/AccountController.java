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
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping("/{accountNumber}")
	@CrossOrigin(origins = "http://localhost:5000")
	public EntityModel<Account> findAccountByNumber(@PathVariable final String accountNumber) {
		final Account account = accountService.findAccountByNumber(accountNumber)
				.orElseThrow(() -> new AccountNotFoundException(accountNumber));
		
		return modelAssembler.toModel(account);
	}

	@GetMapping()
	@CrossOrigin(origins = "http://localhost:5000")
	public EntityModel<Account> findAccountByIdNumber(@RequestParam final String id) {
		final Account account = accountService.findAccountByIdNumber(id)
				.orElseThrow(() -> new AccountNotFoundException(id));
		
		return modelAssembler.toModel(account);
	}

	@PostMapping("/new")
	public ResponseEntity<?> createAccount(@RequestBody final Account account) {
		
		final EntityModel<Account> model = modelAssembler.toModel( accountService.saveAccountData(account) );
		
		return ResponseEntity
				.created(model.getRequiredLink(SELF).toUri())
				.body(model);
	}

	@PutMapping("/update/{accountNumber}")
	public ResponseEntity<?> updateAccount(@RequestBody final Account newAccount, @PathVariable final String accountNumber) {
		final Account updatedAccount = accountService.findAccountByNumber(accountNumber)
				.map(account -> Utils.mapToNewAccount(account, newAccount))
				.orElseThrow(() -> new AccountNotFoundException(accountNumber));
		
		final EntityModel<Account> model = modelAssembler.toModel( accountService.saveAccountData( updatedAccount ));
		
		return ResponseEntity
				.created(model.getRequiredLink(SELF).toUri())
				.body(model);
	}
	
	@DeleteMapping("/{accountNumber}")
	public ResponseEntity<?> deleteAccount(@PathVariable final String accountNumber) {
		final Account account = accountService.findAccountByNumber(accountNumber)
				.orElseThrow(() -> new AccountNotFoundException(accountNumber));
		
		accountService.deleteAccount(account);
		
		return ResponseEntity.noContent().build();
	}

}
