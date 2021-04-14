package com.bezkoder.springbootsecurityjwtmongodb.repository;

import com.bezkoder.springbootsecurityjwtmongodb.models.Article;
import com.bezkoder.springbootsecurityjwtmongodb.models.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PaymentRepository extends MongoRepository<Payment, String> {

    Optional<Payment> findPaymentById(String id);


}
