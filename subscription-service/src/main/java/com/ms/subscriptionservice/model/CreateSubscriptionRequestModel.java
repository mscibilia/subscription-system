package com.ms.subscriptionservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class CreateSubscriptionRequestModel {

    @NotBlank(message = "email address is mandatory")
    private String emailAddress;

    @NotNull(message = "consent flag is mandatory")
    private Boolean consentFlag;

    @NotNull(message = "date of birth is mandatory")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    @NotBlank(message = "newsletter id is mandatory")
    private String newsletterId;

    private String firstName;

    private Gender gender;
}
