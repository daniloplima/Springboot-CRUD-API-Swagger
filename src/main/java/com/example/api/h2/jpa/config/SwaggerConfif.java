package com.example.api.h2.jpa.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfif {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.api.h2.jpa"))
                .paths(regex("/api/v1/todo.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo(){
        ApiInfo apiInfo = new ApiInfo(
                "Lista To-do API REST",
                "API que é uma lista de afazeres com CRUD simples",
                "1.0",
                "terms of service",
                new Contact("Danilo P. Lima", "www.github.com", "danilo.pelozone@gmail.com"),
                "Apache License Version 2.0",
                "hhtps://apache.org.licesen.html", new ArrayList<VendorExtension>()
        );
        return apiInfo;
    }
}
