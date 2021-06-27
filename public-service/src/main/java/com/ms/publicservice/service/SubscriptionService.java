package com.ms.publicservice.service;

import com.ms.publicservice.model.CreateSubscriptionRequestModel;
import com.ms.publicservice.model.UpdateSubscriptionRequestModel;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class SubscriptionService {
    private static final Logger logger = LoggerFactory.getLogger(SubscriptionService.class);

    @Value("${subscription-service.subscriptions.endpoint}")
    private String subscriptionsEndpoint;

    private final WebClient subscriptionServiceWebclient;

    public ResponseEntity<?> getAllSubscriptions() {
        return subscriptionServiceWebclient.get()
                .uri(subscriptionsEndpoint)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(clientResponse ->
                        clientResponse.toEntity(String.class))
                .block();
    }

    public ResponseEntity<?> getSubscription(Long subscriptionId) {
        return subscriptionServiceWebclient.get()
                .uri(subscriptionsEndpoint + "/{id}", subscriptionId)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(clientResponse ->
                        clientResponse.toEntity(String.class))
                .block();
    }

    public ResponseEntity<?> deleteSubscription(long subscriptionId) {
        return subscriptionServiceWebclient.delete()
                .uri(subscriptionsEndpoint + "/{id}", subscriptionId)
                .exchangeToMono(clientResponse ->
                        clientResponse.toEntity(Void.class))
                .block();
    }

    public ResponseEntity updateSubscription(UpdateSubscriptionRequestModel requestModel) {
        return subscriptionServiceWebclient.put()
                .uri(subscriptionsEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestModel))
                .exchangeToMono(clientResponse ->
                        clientResponse.toEntity(Void.class))
                .block();
    }

    public ResponseEntity createSubscription(CreateSubscriptionRequestModel requestModel) {
        return subscriptionServiceWebclient.post()
                .uri(subscriptionsEndpoint)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestModel))
                .exchangeToMono(clientResponse ->
                        clientResponse.toEntity(String.class))
                .block();
    }

}
