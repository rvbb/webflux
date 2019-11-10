package webflux.demo.customer.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import webflux.demo.customer.model.Cif;

@Repository
public interface ICifRepository extends ReactiveMongoRepository<Cif, String> {
	
	//flux will contain many object
	Flux<Cif> findByBase(String base);

	//expect exactly one result
	Mono<Cif> findById(String id);
}
