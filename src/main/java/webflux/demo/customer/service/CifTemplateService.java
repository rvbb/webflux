package webflux.demo.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import webflux.demo.customer.model.Cif;

@Service
public class CifTemplateService {

	@Autowired
	ReactiveMongoTemplate template;

	public Mono<Cif> findById(String id) {
		return template.findById(id, Cif.class);
	}	
}
