package com.jack;

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
 * @author ：liyongjie
 * @ClassName ：EsSwagger
 * @date ： 2019-11-26 09:28
 * @modified By：
 */
@Configuration
@EnableSwagger2
public class EsSwagger {


    //RequestHandlerSelectors.basePackage("com.jack.controller")
    //RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jack.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger")
                .description("swagger")
                .termsOfServiceUrl("http://xxxx.xxxx.com/")
                .contact("jack")
                .version("1.0")
                .build();
    }
}
