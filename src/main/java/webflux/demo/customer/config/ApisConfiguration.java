package webflux.demo.customer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApisConfiguration {

    @Bean(name = "bankListService")
    public WebClient bankListService(@Value("${baseUrl.banklistService}") String baseUrl) {
        return WebClient.builder().baseUrl(baseUrl).build();
    }
	
}
