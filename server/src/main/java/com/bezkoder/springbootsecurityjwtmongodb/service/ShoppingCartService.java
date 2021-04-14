package com.bezkoder.springbootsecurityjwtmongodb.service;

import com.bezkoder.springbootsecurityjwtmongodb.models.Article;
import com.bezkoder.springbootsecurityjwtmongodb.models.ShoppingCart;
import com.bezkoder.springbootsecurityjwtmongodb.repository.ArticleRepo;
import com.bezkoder.springbootsecurityjwtmongodb.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    public List<ShoppingCart> findAllShoppingCart() {
        return shoppingCartRepository.findAll();
    }



    public ShoppingCart findShoppingCartById(String id) {
        return shoppingCartRepository.findById(id)
                .orElseThrow(() -> new Error("ShoppingCart by id " + id + " was not found"));
    }


    public ShoppingCart findShoppingCartByIdUser(String id) {
        return shoppingCartRepository.findById(id)
                .orElseThrow(() -> new Error("ShoppingCart by id " + id + " was not found"));
    }

}
