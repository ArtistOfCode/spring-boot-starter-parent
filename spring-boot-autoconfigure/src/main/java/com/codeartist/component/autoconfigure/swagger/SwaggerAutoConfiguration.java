package com.codeartist.component.autoconfigure.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.time.LocalTime;

import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;

/**
 * Swagger配置
 *
 * @author 艾江南
 * @date 2022/8/18
 */
@SpringBootConfiguration
public class SwaggerAutoConfiguration {

    @Value("${spring.application.name}")
    private String name;

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title(name)
                        .build())
                .groupName(name)
                .directModelSubstitute(LocalTime.class, String.class)
                .select()
                .apis(withClassAnnotation(RestController.class))
                .build();
    }

}
