package com.ancora.services.{{cookiecutter.service_name}};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class {{cookiecutter.service_app_name}}Application extends SpringBootServletInitializer {

	private static Class<{{cookiecutter.service_app_name}}Application> applicationClass = {{cookiecutter.service_app_name}}Application.class;

	public static void main(String[] args) {
		SpringApplication.run(applicationClass, args);
	}

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

}
