package com.bhk.swagger;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	private ApiInfo apiInfo(SwaggerConfigProperties swaggerConfigProperties) {
        return new ApiInfoBuilder().title(swaggerConfigProperties.getTitle()).description(swaggerConfigProperties.getDescription())
                                   .version(swaggerConfigProperties.getApiVersion()).build();
    }
}
