package com.kyokyu.backend.service;

import com.kyokyu.backend.models.Commande;
import com.kyokyu.backend.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Commande findCommandeByUsername(String username) {
        return commandeRepository.findCommandeByUsername(username)
                .orElseThrow(() -> new Error("User by username " + username + " was not found"));
    }

    /*
    public Commande findCommandeByArticleId(String articleId) {
        return commandeRepository.findCommandeByUserName(articleId)
                .orElseThrow(() -> new Error("Article  by articleId " + articleId + " was not found"));
    }*/


}
