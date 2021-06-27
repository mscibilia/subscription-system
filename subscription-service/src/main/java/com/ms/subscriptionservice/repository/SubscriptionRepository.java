package com.ms.subscriptionservice.repository;

import com.ms.subscriptionservice.dto.SubscriptionDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<SubscriptionDto, Long> {
    Optional<SubscriptionDto> findByEmailAddress(String emailAddress);
}
