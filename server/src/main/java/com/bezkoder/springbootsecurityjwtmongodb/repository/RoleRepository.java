package com.bezkoder.springbootsecurityjwtmongodb.repository;

import java.util.Optional;

import com.bezkoder.springbootsecurityjwtmongodb.models.ERole;
import com.bezkoder.springbootsecurityjwtmongodb.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
