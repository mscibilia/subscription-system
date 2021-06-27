package com.ms.publicservice.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateSubscriptionRequestModel extends CreateSubscriptionRequestModel {
    @NotNull(message = "subscription id is mandatory")
    private Long subscriptionId;
}
