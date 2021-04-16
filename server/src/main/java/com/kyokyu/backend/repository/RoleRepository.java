package com.kyokyu.backend.repository;

import com.kyokyu.backend.models.ERole;
import com.kyokyu.backend.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
