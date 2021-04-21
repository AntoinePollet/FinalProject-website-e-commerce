package com.kyokyu.backend.repository;

import com.kyokyu.backend.models.Favorites;
import com.kyokyu.backend.models.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface FavoritesRepository extends MongoRepository<Favorites, String> {

    void deleteFavoritesById(String id);

    List<Favorites> findByUserName(String username);

}