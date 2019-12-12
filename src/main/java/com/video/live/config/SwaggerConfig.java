package com.video.live.config;

import com.video.live.common.properties.SwaggerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2 配置类
 *
 * @Author: Deng Yunhu
 * @Date: 2019/11/25 17:03
 */
@Profile({"dev", "test"})
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private SwaggerProperties properties;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(properties.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(properties.getTile())
                .description(properties.getDescription())
                .termsOfServiceUrl(properties.getTermOfServiceUrl())
                .contact(new Contact(properties.getConcatName(), properties.getContactUrl(), properties.getEmail()))
                .version(properties.getVersion())
                .build();

    }
}
