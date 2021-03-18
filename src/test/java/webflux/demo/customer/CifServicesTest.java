package webflux.demo.customer;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import webflux.demo.customer.service.CifService;

@SpringBootTest
class CifServicesTest {

	@Autowired
	CifService service;
	
	@Test
	void givenX_whenY_thenZ() {
		System.out.println(service);
		assertTrue(true);
	}

}
