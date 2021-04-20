package com.kyokyu.backend.controllers;

import com.kyokyu.backend.models.Commande;
import com.kyokyu.backend.service.CommandeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/command")
public class CommandController {

    private final CommandeService commandeService;

    Logger logger= LoggerFactory.getLogger(ArticleController.class);


    public CommandController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Commande>> getAllCommande () {
        List<Commande> commande = commandeService.findAllCommande();
        logger.info("getAllCommande : {}", commande);
        return new ResponseEntity<>(commande, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Commande> getCommandeById (@PathVariable("id") String id) {
        Commande commande = commandeService.findCommandeById(id);
        logger.info("getCommandeById : {}", commande);
        return new ResponseEntity<>(commande, HttpStatus.OK);
    }

    @GetMapping("/findByUsername/{username}")
    public ResponseEntity<List<Commande>> getCommandeByUsername (@PathVariable("username") String username) {
        List<Commande> commande = commandeService.findCommandeByUsername(username);
        logger.info("getCommandeByUsername : {}", commande);
        return new ResponseEntity<>(commande, HttpStatus.OK);
    }




}
