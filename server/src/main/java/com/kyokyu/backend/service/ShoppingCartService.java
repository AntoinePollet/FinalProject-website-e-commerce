package com.kyokyu.backend.service;

import com.kyokyu.backend.models.CommandeArticles;
import com.kyokyu.backend.models.ShoppingCart;
import com.kyokyu.backend.models.User;
import com.kyokyu.backend.models.UserInfos;
import com.kyokyu.backend.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
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


    public ShoppingCart findShoppingCartByUserName(String username) {
        return shoppingCartRepository.findByIdUserName(username)
                .orElseThrow(() -> new Error("ShoppingCart by username " + username + " was not found"));
    }


    public ShoppingCart updateShoppingCartInfos(String id, List<CommandeArticles> commandeArticles, Double total) {
        Optional<ShoppingCart> shoppingCartData = shoppingCartRepository.findById(id);

        if (shoppingCartData.isPresent()) {
            ShoppingCart _shoppingCart = shoppingCartData.get();
            _shoppingCart.setArticles(commandeArticles);
            _shoppingCart.setDate(new Date());
            _shoppingCart.setTotal(total);


            return shoppingCartRepository.save(_shoppingCart);
        }
        return null;
    }



}
