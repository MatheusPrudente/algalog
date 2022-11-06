package com.algaworks.algalog.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SpringDocConfig {

	final String securitySchemeName = "bearerAuth";

	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI().info(new Info().title("ALGALOG")
				.description("Api Rest de Gerenciamento de Entregas").version("v0.0.1"));
	}

}