package vietteldigital.webback.bankplus.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

import vietteldigital.webback.bankplus.customer.repo.ICifRepository;

@EnableReactiveMongoRepositories(basePackageClasses = ICifRepository.class)
@EnableMongoAuditing
public class EnableReactive extends AbstractReactiveMongoConfiguration {

	@Bean
	public MongoClient mongoClient() {
		return MongoClients.create();
	}

	@Override
	protected String getDatabaseName() {
		return "devdb";
	}

	@Override
	public MongoClient reactiveMongoClient() {
		return mongoClient();
	}

	// instead of Repository, this use Template way
	@Bean
	public ReactiveMongoTemplate reactiveMongoTemplate() {
		return new ReactiveMongoTemplate(mongoClient(), getDatabaseName());
	}

}
