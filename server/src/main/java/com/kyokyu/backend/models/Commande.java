package com.kyokyu.backend.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Data
@Document(collection = "commandes")
public class Commande {

    @Id
    private String id;

    //ref
    private List<CommandeArticles> articles;

    private Date date;

    // ref
    private String username;

    private Double total;

    private Address address;


    public Commande() {
    }

    public Commande(List<CommandeArticles> articles, Date date, String username, Double total, Address address) {
        this.articles = articles;
        this.date = date;
        this.username = username;
        this.total = total;
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CommandeArticles> getArticles() {
        return articles;
    }

    public void setArticles(List<CommandeArticles> Articles) {
        this.articles = articles;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }



}