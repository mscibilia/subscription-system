package com.ms.subscriptionservice.model;

import com.ms.subscriptionservice.dto.SubscriptionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetAllSubscriptionsResponseModel {
    private final List<SubscriptionDto> subscriptions;
}
