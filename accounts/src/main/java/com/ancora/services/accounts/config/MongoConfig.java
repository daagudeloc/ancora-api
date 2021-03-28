package com.ancora.services.accounts.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackages = {"com.ancora.services.accounts.repository"})
public class MongoConfig extends AbstractMongoClientConfiguration {

	@Value("${spring.data.mongodb.uri}")
	private String mongoConnection;
	@Value("${spring.data.mongodb.database}")
	private String mongoDatabaseName;

	@Override
	protected String getDatabaseName() {
		return mongoDatabaseName;
	}

	@Bean
	@Override
	public MongoClient mongoClient() {
		final MongoClient mongoClient = MongoClients.create(mongoConnection);
		return mongoClient;
	}

	

}
