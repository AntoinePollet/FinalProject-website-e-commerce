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

    private Delivery deliveryAddress;


    public Commande() {
    }

    public Commande(List<CommandeArticles> articles, Date date, String username, Double total, Delivery deliveryAddress) {
        this.articles = articles;
        this.date = date;
        this.username = username;
        this.total = total;
        this.deliveryAddress = deliveryAddress;
    }

    public Delivery getDelivery() {
        return deliveryAddress;
    }

    public void setDelivery(Delivery deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
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