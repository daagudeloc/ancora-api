package com.ancora.services.accounts.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.ancora.services.accounts.controller.AccountController;
import com.ancora.services.accounts.domain.Account;

@Component
public class AccountModelAssembler implements RepresentationModelAssembler<Account, EntityModel<Account>>{

	@Override
	public EntityModel<Account> toModel(final Account account) {
		return EntityModel.of(account,
				linkTo(methodOn(AccountController.class).findAccountByNumber(account.getNumber())).withSelfRel(),
				linkTo(methodOn(AccountController.class).findAllAccounts()).withRel("accounts"));
	}

}
