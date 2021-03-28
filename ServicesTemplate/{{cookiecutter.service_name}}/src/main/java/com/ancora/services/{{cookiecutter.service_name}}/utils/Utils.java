package com.ancora.services.{{cookiecutter.service_name}}.utils;

import java.util.function.Function;

import com.ancora.services.{{cookiecutter.service_name}}.domain.{{cookiecutter.service_class_name}};

public class Utils {
	
	public static {{cookiecutter.service_class_name}} mapToNew{{cookiecutter.service_class_name}}(final {{cookiecutter.service_class_name}} {{cookiecutter.service_instance_name}}, final {{cookiecutter.service_class_name}} new{{cookiecutter.service_class_name}}) {
		
		{{cookiecutter.service_instance_name}}.setFirstName(new{{cookiecutter.service_class_name}}.getFirstName());
		{{cookiecutter.service_instance_name}}.setLastName(new{{cookiecutter.service_class_name}}.getLastName());
		{{cookiecutter.service_instance_name}}.setBirthDate(new{{cookiecutter.service_class_name}}.getBirthDate());
		{{cookiecutter.service_instance_name}}.setBalance(new{{cookiecutter.service_class_name}}.getBalance());
		{{cookiecutter.service_instance_name}}.setIdNumber(new{{cookiecutter.service_class_name}}.getIdNumber());
		{{cookiecutter.service_instance_name}}.setNumber(new{{cookiecutter.service_class_name}}.getNumber());
		
		return {{cookiecutter.service_instance_name}};
	}

}
