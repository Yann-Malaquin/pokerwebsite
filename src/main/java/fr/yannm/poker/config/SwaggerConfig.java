package fr.yannm.poker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig is the class that permit to comment the API.
 *
 * @author Yann
 * @version 1.0
 * @name : SwaggerConfig
 * @created 03/08/2021 - 15:28
 * @project poker
 * @copyright Yann
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("fr.yannm.poker.model")
                        .or(RequestHandlerSelectors.basePackage("fr.yannm.poker.controller")))
                .paths(PathSelectors.any())
                .build();
    }

    // Describe your apis
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Poker API")
                .description("API to access to the website Poker")
                .version("0.0.1-SNAPSHOT")
                .build();
    }
}
