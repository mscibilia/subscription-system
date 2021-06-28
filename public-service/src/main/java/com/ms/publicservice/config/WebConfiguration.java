package com.ms.publicservice.config;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;

@Configuration
public class WebConfiguration {

    @Value("${subscription-service.base.url}")
    private String subscriptionServiceBaseUrl;

    @Value("${subscription-service.basic.auth.username}")
    private String subscriptionServiceBasicAuthUsername;

    @Value("${subscription-service.basic.auth.password}")
    private String subscriptionServiceBasicAuthPassword;

    @Bean
    public WebClient subscriptionServiceWebClient() throws SSLException {
        SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();

        HttpClient secureClient = HttpClient.create().secure(t -> t.sslContext(sslContext));

        return WebClient.builder()
                .baseUrl(subscriptionServiceBaseUrl)
                .defaultHeaders(httpHeaders -> httpHeaders.setBasicAuth(subscriptionServiceBasicAuthUsername, subscriptionServiceBasicAuthPassword))
                .clientConnector(new ReactorClientHttpConnector(secureClient))
                .build();
    }

}
