package com.ms.publicservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class CreateSubscriptionRequestModel {

    @NotBlank(message = "email address is mandatory")
    @Email(message = "email address is not in a valid format")
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
