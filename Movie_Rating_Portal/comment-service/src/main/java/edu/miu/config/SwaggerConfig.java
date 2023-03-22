package edu.miu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket configure() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("edu.miu"))
                .build()
                .apiInfo(commentApiDetails());
    }

    private ApiInfo commentApiDetails(){
        return new ApiInfo("Comment Service API","An api for crud operations of comments for movies and series","v1.0","authorized access",
                new Contact("Chandrika Thapa","http://edu.miu","chandrika.thapa@miu.edu"),
                "API license","http://edu.miu", Collections.emptyList());
    }
}
