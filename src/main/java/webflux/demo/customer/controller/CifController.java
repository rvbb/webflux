package webflux.demo.customer.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import webflux.demo.customer.model.Cif;
import webflux.demo.customer.model.CifDto;
import webflux.demo.customer.model.FailureResponse;
import webflux.demo.customer.service.CifService;


@Slf4j
@RestController
@RequestMapping(path = "/customer/cif")
public class CifController {

	@Autowired
	CifService service;

	@ApiOperation(value = "Get cif by name")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "all cif be fetched success", response = Cif.class),
			@ApiResponse(code = 400, message = "Bad request", response = FailureResponse.class) })
	@GetMapping
	@Cacheable("cifs")
	public Flux<Cif> getCifByBase(@Param("base") String base) {
		return service.findByBase(base);
	}
	
	@PostMapping
	@Cacheable("cifcreate")
	public Mono<Cif> createOne(@RequestBody CifDto cifDto) {
		return service.create(cifDto);
	}
	
	@PostMapping(value = "/create-auto")
	@Cacheable("cifcreateAuto")
	public Mono<Cif> createAuto() {
		return service.createFromOtherAPI();
	}

	/**
	 * Evict transaction from cache. Use this method with any data changed.;
	 * 
	 */
	@CacheEvict(cacheNames = { "cifs", "cif", "cifcreate", "cifcreateAuto" }, allEntries = true)
	@DeleteMapping(path = "/cache/evict")
	public void evictCache() {
		log.info("accounts cache has been evicted");
	}

}
