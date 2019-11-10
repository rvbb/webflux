package vietteldigital.webback.bankplus.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vietteldigital.webback.bankplus.customer.model.Cif;
import vietteldigital.webback.bankplus.customer.model.FailureResponse;
import vietteldigital.webback.bankplus.customer.service.CifService;

@Slf4j
@RestController
@RequestMapping(path = "/customer/cif")
@Api(value = "Customer Identity File(CIF) Controller")
public class CifController {

	@Autowired
	CifService service;

	@ApiOperation(value = "Get cif by name")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "all cif be fechted success", response = Cif.class),
			@ApiResponse(code = 400, message = "Bad request", response = FailureResponse.class) })
	@GetMapping(value = "/get/{name}")
	@Cacheable("cifs")
	public Flux<Cif> getCifByBase(String base) {
		return service.findByBase(base);
	}
	
	@ApiOperation(value = "get one cif by ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "cif just fechted success", response = Cif.class),
			@ApiResponse(code = 400, message = "Bad request", response = FailureResponse.class) })
	@GetMapping(value = "/get/{id}")
	@Cacheable("cif")
	public Mono<Cif> getById(String id) {
		return service.findById(id);
	}

	@ApiOperation(value = "Create new one")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "create success", response = Cif.class),
			@ApiResponse(code = 400, message = "Bad request", response = FailureResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = FailureResponse.class)})
	@PostMapping
	@Cacheable("cifcreate")
	public Mono<Cif> createOne(Cif cif) {
		return service.create(cif);
	}
	
	@ApiOperation(value = "Create new one with data from other APIs with: Webclient.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "create success", response = Cif.class),
			@ApiResponse(code = 400, message = "Bad request", response = FailureResponse.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = FailureResponse.class)})
	@PostMapping(value = "/create/auto")
	@Cacheable("cifcreateAuto")
	public Mono<Cif> createAuto() {
		return service.createFromOtherAPI();
	}

	/**
	 * Evict transaction from cache. Use this method with any data changed.;
	 * 
	 */
	@CacheEvict(cacheNames = { "cifs", "cif", "cifcreate", "cifcreateAuto" }, allEntries = true)
	@RequestMapping(path = "/cache/evict", method = RequestMethod.DELETE)
	@ApiOperation(value = "Evict transactions cache")
	public void evictCache() {
		log.info("accounts cache has been evicted");
	}

	/* more api here */
}
