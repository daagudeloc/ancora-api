package com.ancora.services.accounts.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.ancora.services.accounts.domain.Account;

@Repository
public interface AccountRepository extends MongoRepository<Account, Long> {

	@Query("{ 'lastName': ?0 }")
	List<Account> findByLastName(final String lastName);
	
	@Query("{ 'number': ?0 }")
	Optional<Account> findByNumber(final String number);

	@Query("{ 'idNumber': ?0 }")
	Optional<Account> findByIdNumber(final String idNumber);

}
