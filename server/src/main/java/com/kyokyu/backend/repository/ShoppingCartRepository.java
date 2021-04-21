package com.kyokyu.backend.repository;

import com.kyokyu.backend.models.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {
    Optional<ShoppingCart> findByPrice(String price);

    Optional<ShoppingCart> findShoppingCartById(String id);

    Optional<ShoppingCart> findByIdUserName(String username);


}
