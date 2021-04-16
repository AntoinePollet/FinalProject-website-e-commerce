package com.kyokyu.backend.service;

import com.kyokyu.backend.models.ShoppingCart;
import com.kyokyu.backend.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
