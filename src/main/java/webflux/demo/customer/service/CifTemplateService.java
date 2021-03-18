package webflux.demo.customer.service;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import webflux.demo.customer.model.Cif;

@AllArgsConstructor
@Service
public class CifTemplateService {

	private final ReactiveMongoTemplate template;

	public Mono<Cif> findById(String id) {
		return template.findById(id, Cif.class);
	}	
}
