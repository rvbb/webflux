package webflux.demo.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import io.swagger.annotations.Api;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import webflux.demo.customer.model.Cif;
import webflux.demo.customer.service.CifService;
import webflux.demo.customer.model.FailureResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Slf4j
@RestController
@RequestMapping(path = "/customer/cif")
//@Api(value = "Customer Identity File(CIF) Controller")
public class CifController {

	@Autowired
	CifService service;

	@ApiOperation(value = "Get cif by name")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "all cif be fechted success", response = Cif.class),
			@ApiResponse(code = 400, message = "Bad request", response = FailureResponse.class) })
	@GetMapping(value = "/get/{base}")
	@Cacheable("cifs")
	public Flux<Cif> getCifByBase(@PathVariable String base) {
		return service.findByBase(base);
	}
	

//	@ApiOperation(value = "Create new one")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "create success", response = Cif.class),
//			@ApiResponse(code = 400, message = "Bad request", response = FailureResponse.class),
//			@ApiResponse(code = 500, message = "Internal Server Error", response = FailureResponse.class)})
	@PostMapping
	@Cacheable("cifcreate")
	public Mono<Cif> createOne(@RequestBody Cif cif) {
		return service.create(cif);
	}
	
//	@ApiOperation(value = "Create new one with data from other APIs with: Webclient.")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "create success", response = Cif.class),
//			@ApiResponse(code = 400, message = "Bad request", response = FailureResponse.class),
//			@ApiResponse(code = 500, message = "Internal Server Error", response = FailureResponse.class)})
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
	//@ApiOperation(value = "Evict transactions cache")
	public void evictCache() {
		log.info("accounts cache has been evicted");
	}

	/* more api here */
}
