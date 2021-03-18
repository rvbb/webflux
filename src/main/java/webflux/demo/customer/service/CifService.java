package webflux.demo.customer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import webflux.demo.customer.model.BankListDemo;
import webflux.demo.customer.model.Cif;
import webflux.demo.customer.model.CifDto;
import webflux.demo.customer.repo.ICifRepository;
import webflux.demo.customer.service.mapper.CifMapper;
import webflux.demo.customer.util.Constant;

@AllArgsConstructor
@Service
public class CifService {

    private final ICifRepository repository;
    private final WebClient bankListService;

    public Mono<Cif> create(CifDto cifDto) {
        Cif cif = CifMapper.instance.toEntity(cifDto);
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

        Flux<BankListDemo> bankListMono = bankListService.get().uri(Constant.BANKLISTDEMO_URI_ALL.getVal()).retrieve().bodyToFlux(BankListDemo.class);
        cif.setBankList(bankListMono.collectList().block());
        return repository.insert(cif);
    }
}
