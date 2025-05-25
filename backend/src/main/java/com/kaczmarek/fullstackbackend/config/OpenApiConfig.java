package com.kaczmarek.fullstackbackend.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Message API")
                .version("1.0.0")
                .description("Simple API for storing and fetching messages")
                .contact(new Contact()
                    .name("Jacek Kaczmarek")
                    .email("kaczmarek.jacek10@gmail.com")
                    .url("https://github.com/JacekKaczmarek10")));
    }
}