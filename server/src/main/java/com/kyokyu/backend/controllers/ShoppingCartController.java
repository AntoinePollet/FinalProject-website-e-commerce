package com.kyokyu.backend.controllers;


import com.kyokyu.backend.models.Article;
import com.kyokyu.backend.models.ShoppingCart;
import com.kyokyu.backend.repository.ShoppingCartRepository;
import com.kyokyu.backend.service.ShoppingCartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    Logger logger= LoggerFactory.getLogger(ArticleController.class);



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

    @GetMapping("/findByUsername/{username}")
    public ResponseEntity<ShoppingCart> getShoppingCartByUsername (@PathVariable("username") String username) {
        ShoppingCart shoppingCart = shoppingCartService.findShoppingCartByUserName(username);
        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateShoppingCartById(@PathVariable("id") String id, @RequestBody ShoppingCart shoppingCart) {
        ShoppingCart updateShoppingCartById = shoppingCartService.updateShoppingCartInfos(id, shoppingCart.getArticles(), shoppingCart.getTotal());

        if(updateShoppingCartById == null){
            logger.info("shoppingCart not found : {}", updateShoppingCartById);
            return new ResponseEntity<>("shoppingCart not found", HttpStatus.NOT_FOUND);

        }
        logger.info("shoppingCart updated : {}", updateShoppingCartById);
        return new ResponseEntity<>(updateShoppingCartById, HttpStatus.OK);
    }



}


