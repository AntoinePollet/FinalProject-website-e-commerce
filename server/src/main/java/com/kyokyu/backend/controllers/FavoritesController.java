package com.kyokyu.backend.controllers;


import com.kyokyu.backend.models.*;
import com.kyokyu.backend.repository.CommandeRepository;
import com.kyokyu.backend.repository.FavoritesRepository;
import com.kyokyu.backend.repository.ShoppingCartRepository;
import com.kyokyu.backend.service.FavoritesService;
import com.kyokyu.backend.service.ShoppingCartService;
import com.kyokyu.backend.service.StripeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/favorites")
public class FavoritesController {


    private final FavoritesService favoritesService;

    public FavoritesController(FavoritesService favoritesService) {
        this.favoritesService = favoritesService;
    }

    @Autowired
    private FavoritesRepository favoritesRepository;

    Logger logger= LoggerFactory.getLogger(ArticleController.class);



    @GetMapping("/all")
        public ResponseEntity<List<Favorites>> getAllFavorites () {
        List<Favorites> favorites = favoritesService.findAllFavorites();
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Favorites> getFavoritesById (@PathVariable("id") String id) {
        Favorites favorites = favoritesService.findFavoritesById(id);
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }

    @GetMapping("/findByUsername/{id}")
    public ResponseEntity<List<Favorites>> getFavoritesByUsername (@PathVariable("username") String username) {
        List<Favorites> favorites = favoritesService.findFavoritesByUserName(username);
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Favorites> addFavorites(@RequestBody Favorites favorites) {
        Favorites newFavorites = favoritesService.addFavorites(favorites);
        logger.info("favorites added : {}", newFavorites);
        return new ResponseEntity<>(newFavorites, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFavorites(@PathVariable("id") String id) {
        favoritesService.deleteFavorites(id);
        logger.info("favorites deletedById : {}", id);
        return new ResponseEntity<>("favorites " + id + " deleted", HttpStatus.OK);
    }


}