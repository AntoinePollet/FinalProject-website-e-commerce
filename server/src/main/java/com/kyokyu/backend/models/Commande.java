package com.kyokyu.backend.models;

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
@Document(collection = "commandes")
public class Commande {

    @Id
    private String id;

    //ref
    private List<String> idArticle;

    private Date date;

    // ref
    private String username;

    private Double total;


    public Commande() {
    }

    public Commande(List<String> idArticle, Date date, String username, Double total) {
        this.idArticle = idArticle;
        this.date = date;
        this.username = username;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(List<String> idArticle) {
        this.idArticle = idArticle;
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