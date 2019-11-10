package webflux.demo.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import webflux.demo.customer.model.BankListDemo;
import webflux.demo.customer.model.Cif;
import webflux.demo.customer.repo.ICifRepository;

@Service
public class CifTemplateService {

	@Autowired
	ReactiveMongoTemplate template;

	public Mono<Cif> findById(String id) {
		return template.findById(id, Cif.class);
	}	
}
