package com.xybert.springbootknife4j.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xybert
 * @description knife4j配置类
 * @date 2022/11/11 10：20
 */

@Configuration
public class Knife4jConfig {

    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("")
                .pathsToMatch("/**")
                .addOperationCustomizer((operation, handlerMethod) -> operation.addParametersItem(
                        new HeaderParameter().name("groupCode")
                                .example("测试")
                                .description("集团code")
                                .schema(new StringSchema()._default("BR").name("groupCode").description("集团code"))))
                .packagesToScan("com.xybert.springbootknife4j.controller")
                .build();
    }

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("spring-boot-practice")
                        .version("1.0.0")
                        .description("Knife4j在线API接口文档")
                        .termsOfService("https://www.yuque.com/xybert")
                        .license(new License().name("Apache 2.0")
                                .url("https://doc.xiaominfo.com/")));
    }
}
