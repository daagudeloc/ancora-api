package com.ancora.services.{{cookiecutter.service_name}}.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ancora.services.{{cookiecutter.service_name}}.domain.{{cookiecutter.service_class_name}};
import com.ancora.services.{{cookiecutter.service_name}}.repository.{{cookiecutter.service_class_name}}Repository;

import lombok.Data;

@Data
@Service
public class {{cookiecutter.service_class_name}}Service {
	
	@Autowired
	private final {{cookiecutter.service_class_name}}Repository repository;
	
	public List<{{cookiecutter.service_class_name}}> getAll{{cookiecutter.service_class_name}}s() {
		return repository.findAll();
	}
	
	public Optional<{{cookiecutter.service_class_name}}> find{{cookiecutter.service_class_name}}ByNumber(final String number) {
		return repository.findByNumber(number);
	}

	public List<{{cookiecutter.service_class_name}}> find{{cookiecutter.service_class_name}}ByLastName(final String lastName) {
		return repository.findByLastName(lastName);
	}
	
	public {{cookiecutter.service_class_name}} save{{cookiecutter.service_class_name}}Data(final {{cookiecutter.service_class_name}} {{cookiecutter.service_instance_name}}) {
		return repository.save({{cookiecutter.service_instance_name}});
	}
	
	public void delete{{cookiecutter.service_class_name}}(final {{cookiecutter.service_class_name}} {{cookiecutter.service_instance_name}}) {
		repository.delete({{cookiecutter.service_instance_name}});
	}

}
