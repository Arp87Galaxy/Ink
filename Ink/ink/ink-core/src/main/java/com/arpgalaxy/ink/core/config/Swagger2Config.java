package com.arpgalaxy.ink.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author arpgalaxy
 * @date 2020/9/16
 * @email 13605560342@163.com
 * @description
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.arpgalaxy.ink.core.controller"))
                .build()
                .apiInfo(apiInfo()).alternateTypeRules(AlternateTypeRules.newMapRule(String.class,Object.class));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("设置自己的标题")
                .description("添加描述文字")
                .termsOfServiceUrl("http://localhost:8080")//相关网站地址
                .version("2.0") //版本号，在标题旁边
                .build();
    }
}