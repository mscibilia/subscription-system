package com.ms.subscriptionservice.repository;

import com.ms.subscriptionservice.dto.SubscriptionDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<SubscriptionDto, Long> {
}
