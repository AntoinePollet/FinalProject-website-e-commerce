package com.kyokyu.backend.service;

import com.kyokyu.backend.models.Article;
import com.kyokyu.backend.models.CommandeArticles;
import com.kyokyu.backend.models.Favorites;
import com.kyokyu.backend.models.ShoppingCart;
import com.kyokyu.backend.repository.FavoritesRepository;
import com.kyokyu.backend.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FavoritesService {
    private final FavoritesRepository favoritesRepository;

    @Autowired
    public FavoritesService(FavoritesRepository favoritesRepository) {
        this.favoritesRepository = favoritesRepository;
    }


    public List<Favorites> findAllFavorites() {
        return favoritesRepository.findAll();
    }


    public Favorites findFavoritesById(String id) {
        return favoritesRepository.findById(id)
                .orElseThrow(() -> new Error("Favorites by id " + id + " was not found"));
    }

    public List<Favorites> findFavoritesByUserName(String username) {
        return favoritesRepository.findByUserName(username);
    }

    public Favorites addFavorites(Favorites favorites) {
        return favoritesRepository.save(favorites);
    }


    public void deleteFavorites(String id){
        favoritesRepository.deleteFavoritesById(id);
    }







}
