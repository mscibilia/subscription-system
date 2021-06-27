package com.ms.subscriptionservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UpdateSubscriptionRequestModel extends CreateSubscriptionRequestModel {

    @NotNull(message = "subscription id is mandatory")
    private Long subscriptionId;
}
