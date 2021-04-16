package com.kyokyu.backend.repository;

import com.kyokyu.backend.models.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PaymentRepository extends MongoRepository<Payment, String> {
    Optional<Payment> findArticleById(String id);

}

