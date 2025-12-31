package com.bokbidown.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("복비다운 API 명세서")
                        .description("집주인 - 매수자 - 중개사 역경매 시스템 API")
                        .version("1.0.0"));
    }
}