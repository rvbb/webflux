package webflux.demo.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import webflux.demo.customer.model.BankListDemo;
import webflux.demo.customer.model.Cif;
import webflux.demo.customer.repo.ICifRepository;
import webflux.demo.customer.util.IConstant;

@Service
public class CifService {

	@Autowired
	private ICifRepository repository;

	@Autowired
	@Qualifier("banklistService")
	private WebClient banklistService;

	public Mono<Cif> create(Cif cif) {
		return repository.insert(cif);
	}

	public Flux<Cif> findByBase(String base) {
		return repository.findByBase(base);
	}

	public Mono<Cif> findById(String id) {
		return repository.findById(id).switchIfEmpty(Mono.error(new Exception("Customer not found with id: " + id)));
	}

	public Mono<Cif> createFromOtherAPI() {
		Cif cif = new Cif();
		cif.setBase("Bank");
		cif.setIdInjection(false);
		cif.setStrict(true);
		cif.setDescription("a b c");

//		WebClient banklistService = WebClient.create("http://localhost:20191/bank/banklistdemo");
		// banklist will be got from bankplus#banklist_demo api
		Flux<BankListDemo> bankListMono = banklistService.get().uri(IConstant.BANKLISTDEMO_URI_ALL).retrieve().bodyToFlux(BankListDemo.class);
		cif.setBanklist(bankListMono.collectList().block());
		return repository.insert(cif);
	}
}
