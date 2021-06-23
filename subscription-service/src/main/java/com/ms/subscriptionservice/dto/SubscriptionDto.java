package com.ms.subscriptionservice.dto;

import com.ms.subscriptionservice.model.Gender;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Entity
@Table(name="SUBSCRIPTIONS")
public class SubscriptionDto {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String emailAddress;

    @Column(nullable = false)
    private String newsletterId;

    @Column(nullable = false)
    private Boolean consentFlag;

    @Column(nullable = false)
    private Date dateOfBirth;

    @Column
    private String firstName;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;
}
