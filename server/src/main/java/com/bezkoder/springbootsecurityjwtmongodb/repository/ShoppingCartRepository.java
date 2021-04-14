package com.bezkoder.springbootsecurityjwtmongodb.repository;

import com.bezkoder.springbootsecurityjwtmongodb.models.Article;
import com.bezkoder.springbootsecurityjwtmongodb.models.ShoppingCart;
import com.bezkoder.springbootsecurityjwtmongodb.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {
    Optional<ShoppingCart> findByPrice(String price);

    Optional<ShoppingCart> findShoppingCartById(String id);

    Optional<ShoppingCart> findShoppingCartByIdUser(String username);


}
