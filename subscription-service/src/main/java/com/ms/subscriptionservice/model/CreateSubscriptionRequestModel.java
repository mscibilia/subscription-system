package com.ms.subscriptionservice.model;

import lombok.Data;

import java.util.Date;

@Data
public class CreateSubscriptionRequestModel {

    private String emailAddress;
    private Boolean consentFlag;
    private Date dateOfBirth;
    private String newsletterId;
    private String firstName;
    private Gender gender;
}
