package com.kyokyu.backend.controllers;

import com.kyokyu.backend.models.Commande;
import com.kyokyu.backend.service.CommandeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/command")
public class CommandController {

    private final CommandeService commandeService;

    public CommandController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Commande>> getAllCommande () {
        List<Commande> commande = commandeService.findAllCommande();
        return new ResponseEntity<>(commande, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Commande> getCommandeById (@PathVariable("id") String id) {
        Commande commande = commandeService.findCommandeById(id);
        return new ResponseEntity<>(commande, HttpStatus.OK);
    }

    // error not unique result
    @GetMapping("/findByUsername/{username}")
    public ResponseEntity<Commande> getCommandeByUsername (@PathVariable("username") String username) {
        Commande commande = commandeService.findCommandeByUsername(username);
        return new ResponseEntity<>(commande, HttpStatus.OK);
    }


    /*
    @GetMapping("/findByArticleId/{articleId}")
    public ResponseEntity<Commande> getCommandeByArticleId (@PathVariable("articleId") String articleId) {
        Commande commande = commandeService.findCommandeByArticleId (articleId);
        return new ResponseEntity<>(commande, HttpStatus.OK);
    }
    */



}
