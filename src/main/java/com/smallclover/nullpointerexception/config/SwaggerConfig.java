package com.smallclover.nullpointerexception.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Author: Amadeus
 * @Date: 2020/09/08 22:07
 */
@EnableOpenApi
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createSwaggerDoc(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("NPE 文档接口")
                .description("api接口描述")
                .contact(new Contact("叶子", "https://www.github.com/smallclover", "18363998103@163.com"))
                .version("1.0")
                .build();
    }
}
