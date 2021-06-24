package com.ms.subscriptionservice.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class CreateSubscriptionRequestModel {

    @NotNull(message = "email address cannot be null")
    private String emailAddress;

    @NotNull(message = "consent flag cannot be null")
    private Boolean consentFlag;

    @NotNull(message = "date of birth cannot be null")
    private LocalDate dateOfBirth;

    @NotNull(message = "email address cannot be null")
    private String newsletterId;

    private String firstName;

    private Gender gender;
}
