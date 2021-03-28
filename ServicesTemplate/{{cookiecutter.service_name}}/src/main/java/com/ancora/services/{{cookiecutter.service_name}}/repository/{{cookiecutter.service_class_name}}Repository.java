package com.ancora.services.{{cookiecutter.service_name}}.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.ancora.services.{{cookiecutter.service_name}}.domain.{{cookiecutter.service_class_name}};

@Repository
public interface {{cookiecutter.service_class_name}}Repository extends MongoRepository<{{cookiecutter.service_class_name}}, Long> {

	@Query("{ 'lastName': ?0 }")
	List<{{cookiecutter.service_class_name}}> findByLastName(final String lastName);

	@Query("{ 'number': ?0 }")
	Optional<{{cookiecutter.service_class_name}}> findByNumber(final String number);

}
