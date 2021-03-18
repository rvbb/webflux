package webflux.demo.customer.config;

import org.springframework.beans.factory.annotation.Value;

/**@Configuration
@EnableAutoConfiguration(exclude = {R2dbcDataAutoConfiguration.class})*/
public class R2dbcConfig {
    private @Value("${spring.r2dbc.url}") String postgresConn;
}
