package webflux.demo.customer.config;


import org.springframework.beans.factory.annotation.Value;

//@Configuration
//@EnableR2dbcRepositories(basePackages = "webflux.demo.customer.repo")
public class R2dbcPgsqlConfiguration /*extends AbstractR2dbcConfiguration*/ {

    @Value("${spring.data.postgres.host}")
    private String host;
    @Value("${spring.data.postgres.port}")
    private int port;
    @Value("${spring.data.postgres.database}")
    private String databaseName;
    @Value("${spring.data.postgres.username}")
    private String username;
    @Value("${spring.data.postgres.password}")
    private String password;
}
