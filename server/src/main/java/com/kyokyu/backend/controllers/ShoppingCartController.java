package com.kyokyu.backend.controllers;


import com.kyokyu.backend.models.ShoppingCart;
import com.kyokyu.backend.repository.ShoppingCartRepository;
import com.kyokyu.backend.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/shoppingCart")
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


