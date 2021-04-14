package com.bezkoder.springbootsecurityjwtmongodb.controllers;


import com.bezkoder.springbootsecurityjwtmongodb.models.*;
import com.bezkoder.springbootsecurityjwtmongodb.repository.CommandeRepository;
import com.bezkoder.springbootsecurityjwtmongodb.repository.UserRepository;
import com.bezkoder.springbootsecurityjwtmongodb.service.StripeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
//@Controller
public class PaymentController {

    @Value("${stripe.keys.public}")
    private String API_PUBLIC_KEY;

    private StripeService stripeService;

    @Autowired
    CommandeRepository commandeRepository;

    public PaymentController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @GetMapping("/")
    public String homepage() {
        return "homepage";
    }


    @GetMapping("/charge")
    public String chargePage(Model model) {
        model.addAttribute("stripePublicKey", API_PUBLIC_KEY);
        return "charge";
    }

    /*========== REST APIs for Handling Payments ===================*/


    @PostMapping("/create-charge")
    public @ResponseBody
    Response createCharge(String email, String token) {
        //validate data
        if (token == null) {
            return new Response(false, "Stripe payment token is missing. Please, try again later.");
        }

        //create charge

        String chargeId = stripeService.createCharge(email, token, 999); //$9.99 USD
        if (chargeId == null) {
            return new Response(false, "An error occurred while trying to create a charge.");
        }

        // You may want to store charge id along with order information
        return new Response(true, "Success! Your charge id is " + token);
    }

/*
    @PostMapping("/testCharge")
    public ResponseEntity<Article> charge(@RequestBody Article article) {
        Article newArticle = articleService.addArticle(article);
        return new ResponseEntity<>(newArticle, HttpStatus.CREATED);
    }*/



    @PostMapping("/payment")
    public ResponseEntity<?> stripe(@RequestBody Payment payment) {
        //Response stripe(User user, String token, int amount, ShoppingCart userShoppingCart) {
        //validate data
        //@RequestBody List<Commande> commande, String username, String token, int amount
        if (payment.getToken() == null) {
            return new ResponseEntity<>("Stripe payment token is missing. Please, try again later.", HttpStatus.BAD_REQUEST);
        }

        //create charge
        String chargeId = stripeService.createCharge(payment.getUsername(), payment.getToken(), payment.getAmount()); //$9.99 USD
        if (chargeId == null) {
            Commande commande = new Commande(payment.getArray(),new Date(), payment.getUsername(), (double) payment.getAmount());

            commandeRepository.save(commande);
            return new ResponseEntity<>("An error occurred while trying to create a charge.", HttpStatus.NOT_FOUND);
        }

        // You may want to store charge id along with order information

        //We may want to place username instead of a full user or just the id who is string
       /* List<String> supplierNames1 = new ArrayList<String>();
        supplierNames1.add(array);*/

        Commande commande = new Commande(payment.getArray(),new Date(), payment.getUsername(), (double) payment.getAmount());

        commandeRepository.save(commande);

        return new ResponseEntity<>( "Success! Your token is " + payment.getToken(), HttpStatus.OK);
    }


}




























