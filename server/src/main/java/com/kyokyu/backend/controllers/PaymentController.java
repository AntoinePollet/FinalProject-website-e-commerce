package com.kyokyu.backend.controllers;


import com.kyokyu.backend.models.*;
import com.kyokyu.backend.repository.CommandeRepository;
import com.kyokyu.backend.service.StripeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/stripe")
public class PaymentController {





    @Value("${stripe.keys.public}")
    private String API_PUBLIC_KEY;

    private StripeService stripeService;

    @Autowired
    CommandeRepository commandeRepository;

    public PaymentController(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    /*========== REST APIs for Handling Payments ===================*/

    @PostMapping("/payment")
    public ResponseEntity<?> stripe(@RequestBody Payment payment) {

        //validate data
        if (payment.getToken() == null) {
            return new ResponseEntity<>("Stripe payment token is missing. Please, try again later.", HttpStatus.BAD_REQUEST);
        }

        //create charge
        String chargeId = stripeService.createCharge(payment.getUsername(), payment.getToken(), payment.getAmount()); //$9.99 USD

        if (chargeId == null) {
            return new ResponseEntity<>("An error occurred while trying to create a charge.", HttpStatus.NOT_FOUND);
        }

        Commande commande = new Commande(payment.getArray(),new Date(), payment.getUsername(), (double) payment.getAmount());

        commandeRepository.save(commande);

        return new ResponseEntity<>( "Success! Your token is " + payment.getToken(), HttpStatus.OK);
    }


}




























