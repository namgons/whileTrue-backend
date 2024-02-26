package com.whiletruebackend.global.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Getter
public class ConnectionConfig {

    @Value("${spring.notion-api-server.target-url}")
    private String notionApiServerUrl;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
