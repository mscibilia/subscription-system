package com.ms.subscriptionservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
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
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    @NotNull(message = "email address cannot be null")
    private String newsletterId;

    private String firstName;

    private Gender gender;
}
