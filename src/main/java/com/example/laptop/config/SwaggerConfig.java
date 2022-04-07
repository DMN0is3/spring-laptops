package com.example.laptop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * Configuración Swagger - Documentación de API REST<br>
 *
 * HTML: http://localhost:8081/swagger-ui/<br>
 * JSON: http://localhost:8081/v2/api-docs<br>
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiDetails(){
        return new ApiInfo("SpringBoot API REST - Laptops",
                "Documentación API REST Laptop",
                "1.0",
                "http://www.google.com",
                new Contact("Daniel", "http://www.google.com", "danielmorenomula@gmail.com"),
                "MIT",
                "http://www.google.com",
                Collections.emptyList());
    }
}
