package com.bezkoder.springbootsecurityjwtmongodb.repository;

import com.bezkoder.springbootsecurityjwtmongodb.models.Commande;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CommandeRepository extends MongoRepository<Commande, String> {

    Optional<Commande> findCommandeById(String id);

}
