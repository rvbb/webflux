package webflux.demo.customer.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("springweb.demo.customer.controller"))
				.paths(PathSelectors.any()).build().apiInfo(apiInfo()).useDefaultResponseMessages(false);
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("MSA#customer APIs",
				"Spring Web demo", "1.0.0-DEV",
				"https://hoangnv.vn/termofservice_license",
				new Contact("Hoangnv", "https://hoangnv.vn",
						"api_cs@hoangnv.com.vn"),
				"© VDS", "https://hoangnv.vn/termofservice_license", Collections.emptyList());
	}
}
