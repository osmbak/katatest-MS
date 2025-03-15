package com.katatest.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Documentation de l'API")
                        .version("1.0")
                        .description("Documentation interactive des APIs de l'application.")
                        .contact(new Contact()
                                .name("Votre Nom")
                                .email("votre.email@example.com")
                                .url("https://votre-site.com")));
    }
}
