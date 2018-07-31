package com.queue.priorityqueue.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(metaData())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.queue.priorityqueue.controller"))
				.paths(PathSelectors.any())
				.build();
	}
	
	 private ApiInfo metaData() {
	        Contact contact = new Contact("Depain Bhagat", "", "bdepain@gmail.com");
	        return new ApiInfoBuilder()
	                .title("Parking Lot")
	                .description("Api Definitions")
	                .version("1.0.0")
	                .contact(contact)
	                .build();
	    }
}
