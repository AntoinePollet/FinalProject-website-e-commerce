package com.kyokyu.backend.service;

import com.kyokyu.backend.models.Commande;
import com.kyokyu.backend.models.User;
import com.kyokyu.backend.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommandeService {

    private final CommandeRepository commandeRepository;

    @Autowired
    public CommandeService(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    public List<Commande> findAllCommande() {
        return commandeRepository.findAll();
    }

    public Commande findCommandeById(String id) {
        return commandeRepository.findById(id)
                .orElseThrow(() -> new Error("User by id " + id + " was not found"));
    }

    public List<Commande> findCommandeByUsername(String username) {
        return commandeRepository.findAllCommandeByUsername(username);

    }



}
