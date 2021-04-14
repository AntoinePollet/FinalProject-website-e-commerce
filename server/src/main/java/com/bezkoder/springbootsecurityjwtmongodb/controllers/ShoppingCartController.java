package com.bezkoder.springbootsecurityjwtmongodb.controllers;


import com.bezkoder.springbootsecurityjwtmongodb.models.Article;
import com.bezkoder.springbootsecurityjwtmongodb.models.ShoppingCart;
import com.bezkoder.springbootsecurityjwtmongodb.repository.ArticleRepo;
import com.bezkoder.springbootsecurityjwtmongodb.repository.ShoppingCartRepository;
import com.bezkoder.springbootsecurityjwtmongodb.service.ArticleService;
import com.bezkoder.springbootsecurityjwtmongodb.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/shoppingCart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;



    @GetMapping("/all")
    public ResponseEntity<List<ShoppingCart>> getAllShoppingCart () {
        List<ShoppingCart> shoppingCarts = shoppingCartService.findAllShoppingCart();
        return new ResponseEntity<>(shoppingCarts, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ShoppingCart> getShoppingCartById (@PathVariable("id") String id) {
        ShoppingCart shoppingCart = shoppingCartService.findShoppingCartById(id);
        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }




}


