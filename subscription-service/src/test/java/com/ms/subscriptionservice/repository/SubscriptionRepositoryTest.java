package com.ms.subscriptionservice.repository;

import com.ms.subscriptionservice.dto.SubscriptionDto;
import com.ms.subscriptionservice.model.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SubscriptionRepositoryTest {

    @Autowired
    private SubscriptionRepository sut;

    @Test
    public void shouldReturnEntityWithGivenEmailAddress() {
        String emailAddress = "test@test.com";
        SubscriptionDto dto = SubscriptionDto.builder()
                .emailAddress(emailAddress)
                .consentFlag(true)
                .firstName("John")
                .dateOfBirth(LocalDate.now())
                .gender(Gender.MALE)
                .newsletterId("nl1")
                .build();

        sut.save(dto);

        assertTrue(sut.findByEmailAddress(emailAddress).isPresent());
    }
}
