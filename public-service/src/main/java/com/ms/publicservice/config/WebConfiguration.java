package com.ms.publicservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebConfiguration {

    @Value("${subscription-service.base.url}")
    private String subscriptionServiceBaseUrl;

    @Bean
    public WebClient subscriptionServiceWebClient() {
        return WebClient.create(subscriptionServiceBaseUrl);
    }

}
