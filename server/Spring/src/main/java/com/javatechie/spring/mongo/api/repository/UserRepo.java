package com.javatechie.spring.mongo.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.javatechie.spring.mongo.api.model.User;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User, Integer>{
    void deleteUserById(String id);

    Optional<User> findUserById(String id);


}
